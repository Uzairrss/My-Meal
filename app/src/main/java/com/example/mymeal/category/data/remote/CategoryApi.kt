package com.example.mymeal.category.data.remote

import com.example.mymeal.area.data.model.areadetailresponse.AreaDetailResponse
import com.example.mymeal.category.data.model.categorydetailresponse.CategoryDetailResponse
import com.example.mymeal.category.data.model.categorymealresponse.CategoryMealsResponse
import com.example.mymeal.category.data.model.categoryresponse.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApi {

    @GET("categories.php")
    suspend fun getCategoryList(): CategoryResponse

    @GET("filter.php")
    suspend fun getCategoryMealList(
        @Query("c") category: String,
    ): CategoryMealsResponse

    @GET("lookup.php")
    suspend fun getCategoryMealDetail(
        @Query("i") mealId: String,
    ): CategoryDetailResponse
}

