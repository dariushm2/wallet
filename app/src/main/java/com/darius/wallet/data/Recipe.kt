package com.darius.wallet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val ingredients: String,
    val instructions: String,
)