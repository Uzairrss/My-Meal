package com.example.mymeal.category.usecase

import com.example.mymeal.category.data.model.categorydetailresponse.CategoryDetailResponse
import com.example.mymeal.category.data.model.categorymealresponse.CategoryMealsResponse
import com.example.mymeal.category.data.model.categoryresponse.CategoryResponse
import com.example.mymeal.category.repository.ICategoryRepository
import javax.inject.Inject

interface ICategoryUseCase {

    suspend operator fun invoke(): CategoryResponse

    suspend operator fun invoke(category: String): CategoryMealsResponse

    suspend fun getCategoryMealDetail(mealId: String): CategoryDetailResponse
}

class CategoryUseCase @Inject constructor(
    private val repository: ICategoryRepository,
) : ICategoryUseCase {


    override suspend fun invoke(): CategoryResponse {
        return repository.getCategoryList()
    }

    override suspend fun invoke(category: String): CategoryMealsResponse {
        return repository.getCategoryMealList(category)
    }

    override suspend fun getCategoryMealDetail(mealId: String): CategoryDetailResponse {
        return repository.getCategoryMealDetail(mealId)
    }
}
