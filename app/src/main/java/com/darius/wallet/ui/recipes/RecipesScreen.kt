package com.darius.wallet.ui.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.darius.wallet.ui.data.UiState
import com.darius.wallet.ui.errostate.ErrorState
import com.darius.wallet.ui.navigation.Screens

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipesViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state = viewModel.recipes.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchRecipes()
    }

    when (state) {
        is UiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success<*> -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(state.recipes) { _, item ->
                    val recipe = item as UiRecipe
                    Column(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(
                                    route = Screens.Recipe.route +
                                            "?recipe=${recipe.instructions}" +
                                            "&title=${Screens.Recipe.title}",
                                ) {
                                    launchSingleTop = true
                                }
                            }
                    ) {
                        Text(
                            text = recipe.name,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        )
                        HorizontalDivider(
                            color = Color.DarkGray,
                            modifier = Modifier
                                .padding(start = 16.dp)
                        )
                    }
                }
            }
        }

        is UiState.Error -> {
            ErrorState { viewModel.retry() }
        }
    }
}
