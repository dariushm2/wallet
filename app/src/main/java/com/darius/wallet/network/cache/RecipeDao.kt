package com.darius.wallet.network.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.darius.wallet.data.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe)

    @Update
    fun update(recipe: Recipe)



//    @Query("SELECT * FROM recipe_table WHERE id = :id")
//    fun getRecipe(id: String)

    @Query("SELECT * FROM recipe_table")
    fun getRecipes(): List<Recipe>
}