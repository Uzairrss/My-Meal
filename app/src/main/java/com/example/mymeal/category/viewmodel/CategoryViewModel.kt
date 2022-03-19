package com.example.mymeal.category.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.category.data.model.categoryresponse.Category
import com.example.mymeal.category.usecase.ICategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    useCase: ICategoryUseCase,
) : ViewModel() {

    private val _listOfCategory: MutableState<List<Category>> = mutableStateOf(emptyList())
    val listOfCategory: State<List<Category>> = _listOfCategory


    init {
        viewModelScope.launch {
            val categoryResponse = useCase()
            _listOfCategory.value = categoryResponse.categories
        }
    }

}