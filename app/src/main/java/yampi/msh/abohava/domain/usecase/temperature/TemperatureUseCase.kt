package yampi.msh.abohava.domain.usecase.temperature

import kotlinx.coroutines.flow.Flow
import yampi.msh.abohava.domain.model.ForecastParam

interface TemperatureUseCase<out T> {
    suspend fun execute(forecastParam: ForecastParam): Flow<T>
}