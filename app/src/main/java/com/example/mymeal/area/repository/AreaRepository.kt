package com.example.mymeal.area.repository

import com.example.mymeal.area.data.model.areadetailresponse.AreaDetailResponse
import com.example.mymeal.area.data.model.areamealresponse.AreaMealResponse
import com.example.mymeal.area.data.model.arearesponse.AreaResponse
import com.example.mymeal.area.data.remote.AreaApi
import javax.inject.Inject

interface IAreaRepository {

    suspend fun getAreaList(): AreaResponse

    suspend fun getAreaMealsList(area: String): AreaMealResponse

    suspend fun getAreaMealDetail(areaMealId: String): AreaDetailResponse
}

class AreaRepository @Inject constructor(
    private val areaApi: AreaApi,
) : IAreaRepository {

    override suspend fun getAreaList(): AreaResponse {
        return areaApi.getAreaList()
    }

    override suspend fun getAreaMealsList(area: String): AreaMealResponse {
        return areaApi.getAreaMealsList(area)
    }

    override suspend fun getAreaMealDetail(areaMealId: String): AreaDetailResponse {
        return areaApi.getAreaMealDetail(areaMealId)
    }
}

