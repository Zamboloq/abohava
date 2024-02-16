package yampi.msh.abohava.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import yampi.msh.abohava.API_KEY
import yampi.msh.abohava.data.ds.api.AbohavaApiService
import yampi.msh.abohava.domain.model.ForecastParam
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.domain.repository.TemperatureRepository
import javax.inject.Inject

class ForecastTemperatureRepository<out T : ForecastTemperatureModel> @Inject constructor(val abohavaApiService: AbohavaApiService) :
    TemperatureRepository<T> {

    override fun get(forecastParam: ForecastParam): Flow<T> {
        return flow {
            try {
                val forecastTemperatureModel =
                    abohavaApiService.getForecastTemperature(
                        key = API_KEY,
                        city = getForecastLocationParams(forecastParam),
                        days = getForecastDaysParams(forecastParam)
                    )
                Log.d("ForecastTemperatureRepository", forecastTemperatureModel.toString())
                emit(forecastTemperatureModel as T)
            } catch (e: Exception) {
                Log.d("ForecastTemperatureRepository", e.toString())
            }
        }
    }
}