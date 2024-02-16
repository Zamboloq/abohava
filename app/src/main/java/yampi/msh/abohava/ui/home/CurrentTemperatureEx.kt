package yampi.msh.abohava.ui.home

import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.domain.model.TemperatureModel

fun TemperatureModel.empty(): TemperatureModel {
    return TemperatureModel()
}

fun TemperatureModel.toForecast(): ForecastTemperatureModel {
    return ForecastTemperatureModel(this.locationModel, this.currentModel)
}