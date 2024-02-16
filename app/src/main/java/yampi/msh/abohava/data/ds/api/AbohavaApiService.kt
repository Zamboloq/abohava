package yampi.msh.abohava.data.ds.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.domain.model.TemperatureModel

interface AbohavaApiService {
    @Headers(
        "Host: api.weatherapi.com",
        "Cache-Control: max-age=60",
        "Accept-Path: true"
    )
    @GET("current.json")
    suspend fun getCurrentTemperature(@Query("key") key: String, @Query("q") city: String): TemperatureModel

    //forecast
    @Headers(
        "Host: api.weatherapi.com",
        "Cache-Control: max-age=60",
        "Accept-Path: true"
    )
    @GET("forecast.json")
    suspend fun getForecastTemperature(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int
    ): ForecastTemperatureModel

    //forecast
    @Headers(
        "Host: api.weatherapi.com",
        "Cache-Control: max-age=60",
        "Accept-Path: true"
    )
    @GET("forecast.json")
    suspend fun getForecastTemperatureForDays(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("dt") dt: String
    ): ForecastTemperatureModel
}