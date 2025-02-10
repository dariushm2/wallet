package com.darius.wallet.ui.recipes

import com.darius.wallet.data.Recipe
import com.darius.wallet.network.cache.RecipeDao
import com.darius.wallet.network.repositories.RecipesRepo
import com.darius.wallet.ui.data.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class RecipesViewModelTest {

    private lateinit var subject: RecipesViewModel
    private lateinit var recipesRepo: RecipesRepo
    private lateinit var recipeDao: RecipeDao

    @Before
    fun setup() {
        recipesRepo = mock()
        recipeDao = mock()
        subject = RecipesViewModel(recipesRepo, recipeDao, Dispatchers.Unconfined)
    }

    @Test
    fun `verify items from cache are posted`() = runTest {
        val items = listOf(
            Recipe(0, "first", "beans", "cook"),
            Recipe(1, "second", "meat", "barbeque"),
        )
        whenever(recipeDao.getRecipes()).thenReturn(items)
        assert(subject.recipes.value is UiState.Success<*>)
    }
}