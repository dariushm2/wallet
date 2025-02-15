package com.darius.wallet.network.data

import com.google.gson.annotations.SerializedName

data class Recipes(
    @SerializedName("recipes")
    val recipes: List<Recipe>,
)
