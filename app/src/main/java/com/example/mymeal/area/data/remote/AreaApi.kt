package com.example.mymeal.area.data.remote

import com.example.mymeal.area.data.model.areadetailresponse.AreaDetailResponse
import com.example.mymeal.area.data.model.areamealresponse.AreaMealResponse
import com.example.mymeal.area.data.model.arearesponse.AreaResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface AreaApi {

    @GET("list.php?a=list")
    suspend fun getAreaList(): AreaResponse

    @GET("filter.php")
    suspend fun getAreaMealsList(
        @Query("a") area: String,
    ): AreaMealResponse

    @GET("lookup.php")
    suspend fun getAreaMealDetail(
        @Query("i") areaMealId: String,
    ): AreaDetailResponse
}

//filter.php?a=Indian