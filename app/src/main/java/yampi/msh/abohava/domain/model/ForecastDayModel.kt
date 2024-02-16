package yampi.msh.abohava.domain.model

import com.google.gson.annotations.SerializedName

data class ForecastDayModel(
    @SerializedName("date") var date: String? = null,
    @SerializedName("date_epoch") var dateEpoch: Int? = null,
    @SerializedName("day") var day: DayModel? = DayModel(),
    @SerializedName("astro") var astro: AstroModel? = AstroModel(),
    @SerializedName("hour") var hour: ArrayList<HourModel> = arrayListOf()

)
