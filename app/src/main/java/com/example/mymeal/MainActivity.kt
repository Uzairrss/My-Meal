package com.example.mymeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mymeal.navigation.SetupNavGraph
import com.example.mymeal.screen.HomeCategoryScreen
import com.example.mymeal.category.viewmodel.CategoryViewModel
import com.example.mymeal.ui.theme.MyMealTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMealTheme {

                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}
