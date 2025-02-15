package com.darius.wallet.ui.recipe

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.darius.wallet.ui.navigation.Screens

fun NavGraphBuilder.recipeDestination() {
    composable(
        route = Screens.Recipe.route + "?recipe={recipe}&title={title}",
        arguments = listOf(
            navArgument("recipe") {
                type = NavType.StringType
                nullable = true
            },
            navArgument("title") {
                type = NavType.StringType
                nullable = true
            },
        )
    ) {
        RecipeScreen(
            recipe = it.arguments?.getString("recipe"),
        )
    }
}
