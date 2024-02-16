package yampi.msh.abohava.domain.model

import com.google.gson.annotations.SerializedName

data class ForecastTemperatureModel(
    @SerializedName("location") var locationModel: LocationModel? = LocationModel(),
    @SerializedName("current") var currentModel: CurrentModel? = CurrentModel(),
    @SerializedName("forecast") var forecastModel: ForecastModel? = ForecastModel()

)
