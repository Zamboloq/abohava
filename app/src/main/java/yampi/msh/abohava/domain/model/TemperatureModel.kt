package yampi.msh.abohava.domain.model

import com.google.gson.annotations.SerializedName

data class TemperatureModel(
    @SerializedName("location")
    var locationModel: LocationModel? = LocationModel(),
    @SerializedName("current")
    var currentModel: CurrentModel? = CurrentModel()
)