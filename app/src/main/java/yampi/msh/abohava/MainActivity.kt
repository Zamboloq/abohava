package yampi.msh.abohava

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import yampi.msh.abohava.databinding.ActivityMainBinding
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.ui.CitiesView
import yampi.msh.abohava.ui.cities.CitiesViewModel
import yampi.msh.abohava.ui.home.DayCollapsibleToolbarViewLayout
import yampi.msh.abohava.ui.home.TemperatureViewState
import yampi.msh.abohava.ui.home.viewmodel.HomeViewModel
import yampi.msh.abohava.util.isOnline
import java.time.LocalDate


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeViewModel: HomeViewModel by lazy {
        val viewModel: HomeViewModel by viewModels()
        viewModel
    }

    private val citiesViewModel: CitiesViewModel by lazy {
        val viewModel: CitiesViewModel by viewModels()
        viewModel
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        val isConnectionOk = isOnline()
        if (isConnectionOk) {
            Log.i("Internet", "ONLINE")
        } else {
            Log.i("Internet", "OFFLINE")
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        var temperatureState = mutableStateOf(ForecastTemperatureModel())

        setContent {
            val homeTab = TabBarItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )
            val alertsTab = TabBarItem(
                title = "Cities",
                selectedIcon = Icons.Filled.Add,
                unselectedIcon = Icons.Outlined.Add
            )
            val settingsTab = TabBarItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            )
            val moreTab = TabBarItem(
                title = "More",
                selectedIcon = Icons.Filled.MoreVert,
                unselectedIcon = Icons.Outlined.MoreVert
            )

            // creating a list of all the tabs
            val tabBarItems = listOf(homeTab, alertsTab, settingsTab, moreTab)

            // creating our navController
            val navController = rememberNavController()

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                Scaffold(bottomBar = { TabView(tabBarItems, navController) }) {
                    NavHost(navController = navController, startDestination = homeTab.title) {
                        composable(homeTab.title) {
                            homeViewModel.getForecastTemperature()
                            DayCollapsibleToolbarViewLayout(state = temperatureState)
                        }
                        composable(alertsTab.title) {
                            CitiesView(
                                citiesViewModel = citiesViewModel,
                                selectedCity = "",
                                onSelectCity = { city -> citiesViewModel.selectCity(city = city) },
                                addCity = { city -> citiesViewModel.addCityAndRefresh(city = city) },
                            )
                        }
                        composable(settingsTab.title) {
                            Text(settingsTab.title)
                        }
                        composable(moreTab.title) {
                            MoreView()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is TemperatureViewState.Success -> {
                            uiState.forecastTemperatureModel.forecastModel?.forecastday?.let {
                                if (it.isNotEmpty()) {
                                    Log.d(
                                        "HomeFragment",
                                        "Forecast Temperature Model: ${it.first()}"
                                    )
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        for (day in it) {
                                            val d = LocalDate.parse(day.date)
                                            Log.d("HomeFragment", "Day: ${d.dayOfWeek.name}")
                                        }
                                    }
                                }
                            }
                            Log.d(
                                "HomeFragment>>>Forecast",
                                "Forecast for seven days: >>>\n${uiState.forecastTemperatureModel}"
                            )
                            temperatureState.value = uiState.forecastTemperatureModel
                        }

                        is TemperatureViewState.Error -> Log.d(
                            "HomeFragment",
                            "Error: ${uiState.errorMessage}"
                        )
                    }

                }
            }
        }

    }

    data class TabBarItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val badgeAmount: Int? = null
    )

    @Composable
    fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
        var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

        NavigationBar {
            // looping over each tab to generate the views and navigation for each item
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.title)
                    },
                    icon = {
                        TabBarIconView(
                            isSelected = selectedTabIndex == index,
                            selectedIcon = tabBarItem.selectedIcon,
                            unselectedIcon = tabBarItem.unselectedIcon,
                            title = tabBarItem.title,
                            badgeAmount = tabBarItem.badgeAmount
                        )
                    },
                    label = { Text(tabBarItem.title) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            imageVector = if (isSelected) {
                selectedIcon
            } else {
                unselectedIcon
            },
            contentDescription = title
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}
@Composable
fun MoreView() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("TODO: NOT IMPLEMENTED YET")
        Text("TODO: NOT IMPLEMENTED YET")
        Text("TODO: NOT IMPLEMENTED YET")
        Text("TODO: NOT IMPLEMENTED YET")
        Text("TODO: NOT IMPLEMENTED YET")
    }
}