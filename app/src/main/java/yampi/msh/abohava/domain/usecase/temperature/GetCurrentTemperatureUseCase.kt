package yampi.msh.abohava.domain.usecase.temperature

import kotlinx.coroutines.flow.Flow
import yampi.msh.abohava.domain.model.ForecastParam
import yampi.msh.abohava.domain.model.TemperatureModel
import yampi.msh.abohava.domain.repository.TemperatureRepository
import javax.inject.Inject

class GetCurrentTemperatureUseCase @Inject constructor(
    val temperatureRepository: TemperatureRepository<TemperatureModel>,
) : TemperatureUseCase<TemperatureModel> {

    override suspend fun execute(forecastParam: ForecastParam): Flow<TemperatureModel> {
        return temperatureRepository.get(forecastParam = forecastParam)
    }
}