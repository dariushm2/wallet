package com.darius.wallet.ui.recipes

import app.cash.turbine.test
import com.darius.wallet.data.Recipe
import com.darius.wallet.network.repositories.RecipesRepo
import com.darius.wallet.ui.data.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertFailsWith

class RecipesViewModelTest {

    private lateinit var subject: RecipesViewModel
    private lateinit var recipesRepo: RecipesRepo

    @Before
    fun setup() {
        recipesRepo = mock()
        subject = RecipesViewModel(recipesRepo, Dispatchers.Unconfined)
    }

    @Test
    fun `verify success state`() = runTest {
        assert(subject.recipes.value is UiState.Loading)
        val items = flowOf(listOf(
            Recipe(0, "first", "beans", "cook"),
            Recipe(1, "second", "meat", "barbeque"),
        ))
        whenever(recipesRepo.getRecipes()).thenReturn(items)
        subject.fetchRecipes()
        subject.recipes.test {
            assert(awaitItem() is UiState.Success<*>)
        }
    }

    @Test
    fun `verify retry posts success state`() = runTest {
        assert(subject.recipes.value is UiState.Loading)
        val items = flowOf(listOf(
            Recipe(0, "first", "beans", "cook"),
            Recipe(1, "second", "meat", "barbeque"),
        ))
        whenever(recipesRepo.getRecipes()).thenReturn(items)
        subject.fetchRecipes()
        subject.recipes.test {
            assert(awaitItem() is UiState.Success<*>)
        }
        subject.retry()
        subject.recipes.test {
            assert(awaitItem() is UiState.Success<*>)
        }
    }

    @Test
    fun `verify error state`() = runTest {
        assert(subject.recipes.value is UiState.Loading)
        assertFailsWith<Exception> {
            whenever(recipesRepo.getRecipes()).thenThrow(Exception())
            subject.fetchRecipes()
        }
    }
}