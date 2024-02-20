package yampi.msh.abohava.ui

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import yampi.msh.abohava.ui.cities.CitiesViewModel
import yampi.msh.abohava.ui.cities.CitiesViewState

@Composable
fun CitiesView(
    citiesViewModel: CitiesViewModel = viewModel(),
    selectedCity: String,
    addCity: (city: String) -> Unit,
    onSelectCity: (city: String) -> Unit,
) {
    val citiesUiState by citiesViewModel.uiViewState.collectAsState()

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
            when (citiesUiState) {
                is CitiesViewState.City -> {
                    SelectedCityView(city = (citiesUiState as CitiesViewState.City).name.orEmpty())
                }

                is CitiesViewState.Cities -> {
                    LazyColumn {
                        items((citiesUiState as CitiesViewState.Cities).cities) { city ->
                            CityItem(city = city, onItemClick = { selectedCity ->
                                Log.d("CitiesFragment", "$selectedCity is clicked.")
                                citiesViewModel.selectCity(city = city)
                                selectedCityName = selectedCity
                            })
                            Divider(color = Color.Black)
                        }
                    }
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
        .clickable {
            onItemClick(city)
        }) {
        Text(text = city.uppercase(), fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}