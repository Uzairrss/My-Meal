package com.example.mymeal.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.mymeal.area.viewmodel.AreaDetailViewModel

@Composable
fun AreaDetailScreen(
    areaMealId: String?,
    areaDetailViewModel: AreaDetailViewModel = hiltViewModel(),
) {

    areaMealId?.let { areaDetailViewModel.getAreaMealDetail(areaMealId) }

    val listOfMealAreaDetail by remember { areaDetailViewModel.listOfMealAreaDetail }

    LazyColumn {
        items(listOfMealAreaDetail) { mealDetails ->
            mealDetails.strMeal?.let {
                mealDetails.strCategory?.let { it1 ->
                    mealDetails.strMealThumb?.let { it2 ->
                        mealDetails.strArea?.let { it3 ->
                           // mealDetails.strTags?.let { it4 ->
                                mealDetails.strYoutube?.let { it5 ->
                                    mealDetails.strInstructions?.let { it6 ->
                                        DetailSingleItem(
                                            mealName = it,
                                            mealCategory = it1,
                                            mealThumb = it2,
                                            mealArea = it3,
                  //                          mealTag = it4,
                                            mealYoutube = it5,
                                            mealInstruction = it6
                                        )
                                    }
                                }
                            }
                        }
                    }
                //}
            }
        }
    }
}

@Composable
fun DetailSingleItem(
    mealName: String,
    mealCategory: String,
    mealThumb: String,
    mealArea: String,
   // mealTag: String,
    mealYoutube: String,
    mealInstruction: String,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = mealName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier.size(180.dp).clip(RoundedCornerShape(8.dp)),
            painter = rememberImagePainter(mealThumb),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Category: $mealCategory",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 15.dp))
            Text(text = "Area: $mealArea",
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 15.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(100.dp)
        ) {
            Text(text = "#00TAG", modifier = Modifier.padding(start = 15.dp))
            Text(text = "Youtube: $mealYoutube", maxLines = 1, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 15.dp))

        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            elevation = 14.dp,
        ) {
            Text(text = mealInstruction, modifier = Modifier.padding(15.dp))
        }
    }
}
