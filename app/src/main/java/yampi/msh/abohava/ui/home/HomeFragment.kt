package yampi.msh.abohava.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import yampi.msh.abohava.databinding.FragmentHomeBinding
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.ui.dayViewLayout
import yampi.msh.abohava.ui.home.TemperatureViewState.Error
import yampi.msh.abohava.ui.home.TemperatureViewState.Success
import yampi.msh.abohava.ui.home.viewmodel.HomeViewModel
import java.time.LocalDate

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

//    val city = "vilnius"

    private val homeViewModel: HomeViewModel by lazy {
        val viewModel: HomeViewModel by viewModels()
//        viewModel.city = city
        viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var temperatureState = mutableStateOf(ForecastTemperatureModel())
        val composeView: ComposeView = binding.composeView
        composeView.apply {
            setContent {
                dayViewLayout(temperatureState)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is Success -> {
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

                        is Error -> Log.d("HomeFragment", "Error: ${uiState.errorMessage}")
                    }

                }
            }
        }

        homeViewModel.getForecastTemperature()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}