package yampi.msh.abohava.ui.cities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import yampi.msh.abohava.databinding.FragmentCitiesBinding

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val cities = mutableListOf<String>()
    private var selectedCity = "Gorgan"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val citiesViewModel =
            ViewModelProvider(this).get(CitiesViewModel::class.java)

        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val citiesView: ComposeView = binding.citiesView


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                citiesViewModel.uiViewState.collect { uiState ->
                    when (uiState) {
                        is CitiesViewState.City -> {
                            selectedCity = uiState.name.orEmpty()
                            Log.d("CitiesFragment", "Selected city: ${uiState.name}")
                        }

                        is CitiesViewState.Cities -> {
                            Log.d("CitiesFragment", "Cities: ${uiState.cities}")
                            if (uiState.cities.isEmpty()) {
                                cities.clear()
                            } else {
                                cities.clear()
                                cities.addAll(uiState.cities)
                            }
                        }
                    }
                }
            }
        }

        citiesViewModel.getCities()
        citiesViewModel.getSelectedCity()

        citiesView.apply {
            setContent {
                CitiesView(
                    cities = cities,
                    selectedCity = selectedCity,
                    onSelectCity = { city -> citiesViewModel.selectCity(city = city) },
                    addCity = { city -> citiesViewModel.addCityAndRefresh(city = city) })
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun CitiesView(
    cities: MutableList<String>,
    selectedCity: String,
    addCity: (city: String) -> Unit,
    onSelectCity: (city: String) -> Unit,
) {
    var citiesState by remember {
        mutableStateOf(cities)
    }
    var visible by remember {
        mutableStateOf(true)
    }
    var selectedCityName by remember {
        mutableStateOf(selectedCity)
    }
    val animatedAlpha by animateFloatAsState(
        targetValue = if (visible) 1.0f else 0f,
        label = "alpha"
    )
    Column(modifier = Modifier.padding(all = 16.dp)) {
        AddCityView(onSendCity = { city -> addCity(city) })
        SelectedCityView(city = selectedCityName)
        Box(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = animatedAlpha }
            .padding(16.dp)) {
            LazyColumn {
                items(citiesState) { city ->
                    CityItem(city = city, onItemClick = { selectedCity ->
                        Log.d("CitiesFragment", "$selectedCity is clicked.")
                        onSelectCity(selectedCity)
                        selectedCityName = selectedCity
                    })
                    Divider(color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun SelectedCityView(city: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Selected City:", fontSize = 16.sp)
            Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
            Text(text = city.uppercase(), fontSize = 18.sp)
        }
    }
}

@Composable
fun AddCityView(onSendCity: (city: String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("City", fontSize = 16.sp) }
            )
            Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
            Button(onClick = {
                onSendCity(text)
                text = ""
            }) {
                Text("ADD", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun CityItem(city: String, onItemClick: (String) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
//        .background(setBackgroundColor(true))
//        .clip(RoundedCornerShape(8.dp))
        .clickable {
            onItemClick(city)
        }) {
        Text(text = city.uppercase(), fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}