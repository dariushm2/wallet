package com.darius.wallet.ui.recipes

import com.darius.wallet.data.UiData

data class UiRecipe(
    override val id: Int,
    val name: String,
    val instructions: String,
) : UiData(id)