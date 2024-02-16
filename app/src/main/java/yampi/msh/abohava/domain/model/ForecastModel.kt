package yampi.msh.abohava.domain.model

import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("forecastday") var forecastday: ArrayList<ForecastDayModel> = arrayListOf()
)
