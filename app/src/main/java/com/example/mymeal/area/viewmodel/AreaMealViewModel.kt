package com.example.mymeal.area.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.area.data.model.areamealresponse.AreaMeal
import com.example.mymeal.area.usecase.IAreaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaMealViewModel @Inject constructor(
    private val useCase: IAreaUseCase,
) : ViewModel() {

    private val _listOfAreaMeals: MutableState<List<AreaMeal>> = mutableStateOf(emptyList())
    val listOfAreaMeals: State<List<AreaMeal>> = _listOfAreaMeals

    fun getAreaMealList(areaMealName: String) {
        viewModelScope.launch {
            val listOfAreaMeals = useCase(areaMealName)
            _listOfAreaMeals.value = listOfAreaMeals.meals
        }
    }
}