package com.darius.wallet.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    TopAppBar(
        title = {
            currentBackStackEntry.value
                ?.arguments
                ?.getString("title")
                ?.let {
                    Text(it)
                }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        navigationIcon = {
            val title = currentBackStackEntry
                .value
                ?.arguments
                ?.getString("title")
            val showBackButton = title != null && title != "Recipes"
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        modifier = modifier
            .shadow(elevation = 4.dp)
    )
}
