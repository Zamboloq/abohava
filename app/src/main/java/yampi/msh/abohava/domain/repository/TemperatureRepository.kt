package yampi.msh.abohava.domain.repository

import kotlinx.coroutines.flow.Flow
import yampi.msh.abohava.domain.model.ForecastParam

interface TemperatureRepository<out T> {
    fun get(forecastParam: ForecastParam): Flow<T>
}