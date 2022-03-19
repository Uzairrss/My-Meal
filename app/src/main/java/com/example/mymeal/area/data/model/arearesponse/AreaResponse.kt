package com.example.mymeal.area.data.model.arearesponse

import com.google.gson.annotations.SerializedName

data class AreaResponse(
    @SerializedName("meals")
    val area: List<Area>
)