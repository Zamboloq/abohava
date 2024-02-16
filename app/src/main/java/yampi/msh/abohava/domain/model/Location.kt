package yampi.msh.abohava.domain.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name") val name: String? = "",
    @SerializedName("region") val region: String? = "",
    @SerializedName("country") val country: String? = "",
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("lon") val lon: Double? = null,
    @SerializedName("tz_id") val tzId: String? = "",
    @SerializedName("localtime_epoch") val localtimeEpoch: Int? = null,
    @SerializedName("localtime") val localtime: String? = ""

)