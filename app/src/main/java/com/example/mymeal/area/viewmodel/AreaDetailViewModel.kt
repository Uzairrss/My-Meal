package com.example.mymeal.area.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.area.data.model.areadetailresponse.AreaDetailMeal
import com.example.mymeal.area.usecase.IAreaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaDetailViewModel @Inject constructor(
    private val useCase: IAreaUseCase,
) : ViewModel() {

    private val _listOfMealAreaDetail: MutableState<List<AreaDetailMeal>> =
        mutableStateOf(emptyList())
    val listOfMealAreaDetail: State<List<AreaDetailMeal>> = _listOfMealAreaDetail

    fun getAreaMealDetail(areaMealId: String) {
        viewModelScope.launch {
            val listOfMealDetail = useCase.getAreaMealDetail(areaMealId)
            _listOfMealAreaDetail.value = listOfMealDetail.meals
        }
    }

}