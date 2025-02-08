package com.darius.wallet.network.data

import com.google.gson.annotations.SerializedName

data class Recipes(
    @SerializedName("recipes")
    val recipes: List<Recipe>
)

data class Recipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
)