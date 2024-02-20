package yampi.msh.abohava.ui.cities

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import yampi.msh.abohava.domain.usecase.city.AddCityUseCase
import yampi.msh.abohava.domain.usecase.city.GetCitiesUseCase
import yampi.msh.abohava.domain.usecase.city.selected.GetSelectedCityUseCase
import yampi.msh.abohava.domain.usecase.city.selected.OnSelectCityUseCase
import yampi.msh.abohava.ui.cities.CitiesViewState.Cities
import yampi.msh.abohava.ui.cities.CitiesViewState.City
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val addCityUseCase: AddCityUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getSelectedCityUseCase: GetSelectedCityUseCase,
    private val onSelectCityUseCase: OnSelectCityUseCase
) : ViewModel() {

    private val _uiViewState = MutableStateFlow<CitiesViewState>(City("Gorgan"))
    val uiViewState: StateFlow<CitiesViewState> = _uiViewState


    fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            getCitiesUseCase.execute().collect { cities -> _uiViewState.value = Cities(cities) }
        }
    }

    fun addCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addCityUseCase.execute(city = city)
        }
    }

    fun addCityAndRefresh(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            coroutineScope {
                refreshAfterResult(addCityUseCase.execute(city = city))
            }

        }
    }

    fun selectCity(city: String) {
        Log.d("CitiesFragment", "ViewModel -> city: $city")
        viewModelScope.launch(Dispatchers.IO) {
            onSelectCityUseCase.execute(city = city)
        }
    }

    fun getSelectedCity() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiViewState.value = City(getSelectedCityUseCase.execute())
        }
    }

    private fun refreshAfterResult(result: Long) {
        getCities()
    }
}

sealed interface CitiesViewState {
    class Cities(val cities: List<String>) : CitiesViewState
    class City(val name: String? = "") : CitiesViewState
}