package com.example.mymeal.category.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.category.data.model.categorymealresponse.CategoryMeal
import com.example.mymeal.category.usecase.ICategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMealViewModel @Inject constructor(
    private val useCase: ICategoryUseCase,
) : ViewModel() {

    private val _listOfCategoryMeals: MutableState<List<CategoryMeal>> = mutableStateOf(emptyList())
    val listOfCategoryMeals: State<List<CategoryMeal>> = _listOfCategoryMeals

    fun getCategoryMealList(category: String) {
        viewModelScope.launch {
            val listOfCategoryMeals = useCase(category)
            _listOfCategoryMeals.value = listOfCategoryMeals.meals
        }
    }
}