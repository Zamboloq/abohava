package yampi.msh.abohava.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import yampi.msh.abohava.domain.model.ForecastParam
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.domain.model.Location
import yampi.msh.abohava.domain.model.TemperatureModel
import yampi.msh.abohava.domain.usecase.city.selected.GetSelectedCityUseCase
import yampi.msh.abohava.domain.usecase.temperature.TemperatureUseCase
import yampi.msh.abohava.ui.home.TemperatureViewState
import yampi.msh.abohava.ui.home.toForecast
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentTemperatureUseCase: TemperatureUseCase<TemperatureModel>,
    private val getForecastTemperatureUseCase: TemperatureUseCase<ForecastTemperatureModel>,
    private val getSelectedCityUseCase: GetSelectedCityUseCase,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<TemperatureViewState>(TemperatureViewState.Success(ForecastTemperatureModel()))
    val uiState: StateFlow<TemperatureViewState> = _uiState

    var city: String = "Gorgan"


//    fun getSelectedCity() {
//        viewModelScope.launch(Dispatchers.IO) {
//            getSelectedCityUseCase.execute().collect { selectedCity -> city = selectedCity }
//        }
//    }

    fun getCurrentTemperature(city: String) {
        viewModelScope.launch {
            getCurrentTemperatureUseCase.execute(
                forecastParam = ForecastParam(
                    location = Location(name = city),
                    days = 10
                )
            )
                .flowOn(Dispatchers.IO)
                .catch { exception -> _uiState.value = TemperatureViewState.Error(exception.message.orEmpty()) }
                .collect { currentTemperature ->
                    _uiState.value = TemperatureViewState.Success(currentTemperature.toForecast())
                }
        }
    }

    //54.62427286373671, 25.30575850078279
    fun _getForecastTemperature(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getForecastTemperatureUseCase.execute(
                forecastParam = ForecastParam(
                    location = Location(name = city /*lat = 54.62427286373671, lon = 25.30575850078279*/),
                    days = 10
                )
            )
                .catch { exception -> _uiState.value = TemperatureViewState.Error(exception.message.orEmpty()) }
                .collect { forecastTemperature -> _uiState.value = TemperatureViewState.Success(forecastTemperature) }
        }
    }

    fun getForecastTemperature() {
        viewModelScope.launch(Dispatchers.IO) {
            coroutineScope {
                val selectedCity = async { getSelectedCityUseCase.execute() }
                _getForecastTemperature(city = selectedCity.await())
            }

        }
    }
}