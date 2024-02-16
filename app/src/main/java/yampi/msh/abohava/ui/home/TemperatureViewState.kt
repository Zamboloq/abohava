package yampi.msh.abohava.ui.home

import yampi.msh.abohava.domain.model.ForecastTemperatureModel

sealed interface TemperatureViewState {

    data class Success(val forecastTemperatureModel: ForecastTemperatureModel) : TemperatureViewState
    data class Error(val errorMessage: String) : TemperatureViewState

}
