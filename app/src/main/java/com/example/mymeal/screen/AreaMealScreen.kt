package com.example.mymeal.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.mymeal.area.viewmodel.AreaMealViewModel

@Composable
fun AreaMealScreen(
    areaMealName: String?,
    viewModel: AreaMealViewModel = hiltViewModel(),
    onMealClick: (String) -> Unit,
) {
    areaMealName?.let { viewModel.getAreaMealList(areaMealName) }
    val a = areaMealName

    val listOfAreaMeals by remember { viewModel.listOfAreaMeals }

    Column(
        Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$areaMealName Recipes",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(vertical = 15.dp),
        )
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOfAreaMeals) { areaMeals ->
                AreaSingleMeal(
                    name = areaMeals.strMeal,
                    image = areaMeals.strMealThumb,
                    mealId = areaMeals.idMeal
                ) {
                    onMealClick(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AreaSingleMeal(
    name: String,
    image: String,
    mealId: String,
    onMealClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 3.dp,
        onClick = { onMealClick(mealId) }
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(90.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                painter = rememberImagePainter(image),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
    }
}




