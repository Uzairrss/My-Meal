package com.example.mymeal.category.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.area.data.model.areadetailresponse.AreaDetailMeal
import com.example.mymeal.area.usecase.IAreaUseCase
import com.example.mymeal.category.data.model.categorydetailresponse.CategoryDetailMeal
import com.example.mymeal.category.usecase.ICategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val useCase: ICategoryUseCase,
) : ViewModel() {

    private val _listOfMealCategoryDetail: MutableState<List<CategoryDetailMeal>> =
        mutableStateOf(emptyList())
    val listOfMealCategoryDetail: State<List<CategoryDetailMeal>> = _listOfMealCategoryDetail

    fun getCategoryMealDetail(mealId: String) {
        viewModelScope.launch {
            val listOfMealDetail = useCase.getCategoryMealDetail(mealId)
            _listOfMealCategoryDetail.value = listOfMealDetail.meals
        }
    }

}