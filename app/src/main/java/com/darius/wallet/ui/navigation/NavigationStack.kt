package com.darius.wallet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.darius.wallet.ui.recipe.recipeDestination
import com.darius.wallet.ui.recipes.recipesDestination

@Composable
fun NavigationStack(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Recipes.route + "?title=${Screens.Recipes.title}",
    ) {
        recipesDestination(navController)
        recipeDestination()
    }
}