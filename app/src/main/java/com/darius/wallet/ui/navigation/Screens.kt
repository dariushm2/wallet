package com.darius.wallet.ui.navigation

sealed class Screens(
    val route: String,
    val title: String,
) {
    data object Recipes : Screens("recipes", "Recipes")
    data object Recipe : Screens("recipe", "Recipe")
}
