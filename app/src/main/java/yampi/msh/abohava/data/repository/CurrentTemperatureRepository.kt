package yampi.msh.abohava.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import yampi.msh.abohava.API_KEY
import yampi.msh.abohava.data.ds.api.AbohavaApiService
import yampi.msh.abohava.domain.model.ForecastParam
import yampi.msh.abohava.domain.model.TemperatureModel
import yampi.msh.abohava.domain.repository.TemperatureRepository
import javax.inject.Inject

class CurrentTemperatureRepository<out T : TemperatureModel> @Inject constructor(val abohavaApiService: AbohavaApiService) :
    TemperatureRepository<T> {

    override fun get(forecastParam: ForecastParam): Flow<T> {
        return flow {
            val temperatureModel =
                abohavaApiService.getCurrentTemperature(key = API_KEY, city = getForecastLocationParams(forecastParam))
            emit(temperatureModel as T)
        }
    }
}