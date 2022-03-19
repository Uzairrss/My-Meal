package com.example.mymeal.area.usecase

import com.example.mymeal.area.data.model.areadetailresponse.AreaDetailResponse
import com.example.mymeal.area.data.model.areamealresponse.AreaMealResponse
import com.example.mymeal.area.data.model.arearesponse.AreaResponse
import com.example.mymeal.area.repository.IAreaRepository
import javax.inject.Inject

interface IAreaUseCase {

    suspend operator fun invoke(): AreaResponse

    suspend operator fun invoke(area: String): AreaMealResponse

    suspend fun getAreaMealDetail(areaMealId: String): AreaDetailResponse
}

class AreaUseCase @Inject constructor(
    private val repo: IAreaRepository,
) : IAreaUseCase {

    override suspend operator fun invoke(): AreaResponse {
        return repo.getAreaList()
    }

    override suspend operator fun invoke(area: String): AreaMealResponse {
        return repo.getAreaMealsList(area)
    }

    override suspend fun getAreaMealDetail(areaMealId: String): AreaDetailResponse {
        return repo.getAreaMealDetail(areaMealId)
    }
}
