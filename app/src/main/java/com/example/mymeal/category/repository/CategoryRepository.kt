package com.example.mymeal.category.repository

import com.example.mymeal.area.data.model.areadetailresponse.AreaDetailResponse
import com.example.mymeal.category.data.model.categorydetailresponse.CategoryDetailResponse
import com.example.mymeal.category.data.model.categorymealresponse.CategoryMealsResponse
import com.example.mymeal.category.data.model.categoryresponse.CategoryResponse
import com.example.mymeal.category.data.remote.CategoryApi
import javax.inject.Inject

interface ICategoryRepository {

    suspend fun getCategoryList(): CategoryResponse

    suspend fun getCategoryMealList(category: String): CategoryMealsResponse

    suspend fun getCategoryMealDetail(mealId: String): CategoryDetailResponse
}

class CategoryRepository @Inject constructor(
    private val categoryApi: CategoryApi,
) : ICategoryRepository {

    override suspend fun getCategoryList(): CategoryResponse {
        return categoryApi.getCategoryList()
    }

    override suspend fun getCategoryMealList(category: String): CategoryMealsResponse {
        return categoryApi.getCategoryMealList(category)
    }

    override suspend fun getCategoryMealDetail(mealId: String): CategoryDetailResponse {
        return categoryApi.getCategoryMealDetail(mealId)
    }
}


