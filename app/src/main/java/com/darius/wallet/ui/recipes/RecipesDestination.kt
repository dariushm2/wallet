package com.darius.wallet.ui.recipes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.darius.wallet.ui.navigation.Screens


fun NavGraphBuilder.recipesDestination(
    navController: NavHostController,
) {
    composable(
        route = Screens.Recipes.route + "?title={title}",
        arguments = listOf(
            navArgument("title") {
                type = NavType.StringType
                nullable = true
            },
        )
    ) {
        RecipesScreen(navController = navController)
    }
}