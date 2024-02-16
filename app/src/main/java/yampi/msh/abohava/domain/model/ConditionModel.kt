package yampi.msh.abohava.domain.model

import com.google.gson.annotations.SerializedName

data class ConditionModel(
    @SerializedName("text") var text: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("code") var code: Int? = null
)
