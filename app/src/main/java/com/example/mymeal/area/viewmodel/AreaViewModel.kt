package com.example.mymeal.area.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.area.data.model.areamealresponse.AreaMeal
import com.example.mymeal.area.data.model.arearesponse.Area
import com.example.mymeal.area.usecase.IAreaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(
    private val useCase: IAreaUseCase,
) : ViewModel() {

    private val _listOfArea: MutableState<List<Area>> = mutableStateOf(emptyList())
    val listOfArea: State<List<Area>> = _listOfArea

    private val _areaName: MutableState<String> = mutableStateOf("")
    val areaName: State<String> = _areaName

    private val _listOfAreaMeals: MutableState<List<AreaMeal>> = mutableStateOf(emptyList())
    val listOfAreaMeals: State<List<AreaMeal>> = _listOfAreaMeals

    init {
        viewModelScope.launch {
            val areaList = useCase()
            _listOfArea.value = areaList.area
        }
    }

    fun getAreaMealList(area: String) {
        viewModelScope.launch {
            _areaName.value = area

            val areaMealList = useCase(area)
            _listOfAreaMeals.value = areaMealList.meals
        }
    }
}
