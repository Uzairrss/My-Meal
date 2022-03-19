package com.example.mymeal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mymeal.screen.*

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable(route = "home_screen") {
            HomeCategoryScreen(
                onCategoryClicked = { categoryName ->
                    navController.navigate("category_meal_screen/${categoryName}")
                },
                onAreaMealClicked = { areaMealId ->
                    navController.navigate("area_detail_meal_screen/${areaMealId}")
                }
            )
        }


        composable(route = "category_meal_screen/{categoryName}",
            arguments = listOf(navArgument("categoryName") {
                type = NavType.StringType
            })) {
            val categoryName = it.arguments?.getString("categoryName")
            CategoryMealScreen(categoryName = categoryName) { CategorymealId ->
                navController.navigate("category_detail_meal_screen/${CategorymealId}")
            }
        }

        composable(route = "category_detail_meal_screen/{CategorymealId}",
            arguments = listOf(navArgument("CategorymealId") {
                type = NavType.StringType
            })) {
            val CategorymealId = it.arguments?.getString("CategorymealId")
            CategoryDetailScreen(CategorymealId = CategorymealId)
        }



        /*composable(route = "area_meal_screen/{areaMealName}",
            arguments = listOf(navArgument("areaMealName") {
                type = NavType.StringType
            })) {
            val areaMealName = it.arguments?.getString("areaMealName")
            AreaMealScreen(areaMealName = areaMealName) { mealId ->

                navController.navigate("area_detail_meal_screen/${mealId}")

            }
        }*/

        composable(route = "area_detail_meal_screen/{areaMealId}",
            arguments = listOf(navArgument("areaMealId") {
                type = NavType.StringType
            })) {

            val areaMealId = it.arguments?.getString("areaMealId")
            AreaDetailScreen(areaMealId = areaMealId)
        }
    }
}