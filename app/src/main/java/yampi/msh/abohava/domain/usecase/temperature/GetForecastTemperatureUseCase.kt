package yampi.msh.abohava.domain.usecase.temperature

import kotlinx.coroutines.flow.Flow
import yampi.msh.abohava.domain.model.ForecastParam
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.domain.repository.TemperatureRepository
import javax.inject.Inject

class GetForecastTemperatureUseCase @Inject constructor(
    val temperatureRepository: TemperatureRepository<ForecastTemperatureModel>
) : TemperatureUseCase<ForecastTemperatureModel> {
    override suspend fun execute(forecastParam: ForecastParam): Flow<ForecastTemperatureModel> {
        return temperatureRepository.get(forecastParam = forecastParam)
    }
}