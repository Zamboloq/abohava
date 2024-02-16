package yampi.msh.abohava.data.repository

import yampi.msh.abohava.domain.model.ForecastParam

const val CITY_DEFAULT = "Gorgan"
internal fun getForecastLocationParams(forecastParam: ForecastParam): String {
    return if (forecastParam.location.lat != null && forecastParam.location.lon != null) {
        "${forecastParam.location.lat},${forecastParam.location.lon}"
    } else if (forecastParam.location.name.isNullOrEmpty().not()) {
        forecastParam.location.name ?: CITY_DEFAULT
    } else {
        CITY_DEFAULT
    }
}

internal fun getForecastDaysParams(forecastParam: ForecastParam) = forecastParam.days