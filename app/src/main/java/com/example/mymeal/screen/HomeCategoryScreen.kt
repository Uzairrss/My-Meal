package com.example.mymeal.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.mymeal.area.data.model.arearesponse.Area
import com.example.mymeal.area.viewmodel.AreaViewModel
import com.example.mymeal.category.viewmodel.CategoryViewModel

@Composable
fun HomeCategoryScreen(
    onAreaMealClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
) {
    Column {
        SearchBar()
        AreaList()
        HomeList(
            onAreaMealClicked = onAreaMealClicked,
            onCategoryClicked = onCategoryClicked
        )
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        label = { Text(text = "Search...") },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun AreaList(
    viewModel: AreaViewModel = hiltViewModel(),
) {
    val areaList by remember { viewModel.listOfArea }

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = areaList) { area ->
            SingleRowItem(area)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SingleRowItem(
    area: Area,
    viewModel: AreaViewModel = hiltViewModel(),
) {
    Card(
        modifier = Modifier.padding(4.dp),
        shape = CircleShape,
        elevation = 4.dp,
        onClick = {
            area.strArea?.let {
                viewModel.getAreaMealList(it)
            }
        }
    ) {
        area.strArea?.let {
            Text(text = it, modifier = Modifier.padding(4.dp), fontStyle = FontStyle.Italic)
        }
    }
}


@Composable
fun HomeList(
    viewModelCategory: CategoryViewModel = hiltViewModel(),
    viewModelArea: AreaViewModel = hiltViewModel(),
    onCategoryClicked: (String) -> Unit,
    onAreaMealClicked: (String) -> Unit,
) {
    val listOfCategory by remember { viewModelCategory.listOfCategory }
    val listOfAreaMeals by remember { viewModelArea.listOfAreaMeals }
    val areaName by remember { viewModelArea.areaName }

    Column {
        Text(if (listOfAreaMeals.isNullOrEmpty()) "Our Categories.." else "$areaName Recipe..",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(start = 12.dp, top = 110.dp)
        )

        if (listOfAreaMeals.isNullOrEmpty()) {
            LazyColumn(
                Modifier.padding(bottom = 15.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(items = listOfCategory) { category ->
                    SingleColumnItem(
                        name = category.strCategory,
                        image = category.strCategoryThumb,
                    ) {
                        onCategoryClicked(
                            category.strCategory
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                Modifier.padding(top = 10.dp, bottom = 15.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = listOfAreaMeals) { areaMeals ->
                    SingleColumnItem(
                        name = areaMeals.strMeal,
                        image = areaMeals.strMealThumb,
                    ) {
                        onAreaMealClicked(
                            areaMeals.idMeal
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SingleColumnItem(
    image: String,
    name: String,
    onHomeListItemClicked: (String) -> Unit,
) {
    Row(
        Modifier.fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
            .clickable { onHomeListItemClicked(String()) },
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            modifier = Modifier.size(80.dp).clip(shape = CircleShape),
            painter = rememberImagePainter(image), contentDescription = ""
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(name, fontSize = 18.sp, modifier = Modifier.weight(1f))
    }
}
