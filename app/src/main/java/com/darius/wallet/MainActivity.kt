package com.darius.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.darius.wallet.network.ConnectivityHandler
import com.darius.wallet.ui.DebugButton
import com.darius.wallet.ui.TopBar
import com.darius.wallet.ui.errostate.NetworkLoss
import com.darius.wallet.ui.navigation.NavigationStack
import com.darius.wallet.ui.theme.WalletTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityHandler: ConnectivityHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val isConnected = connectivityHandler.isConnected.collectAsState()


            WalletTheme {
                Scaffold(
                    topBar = {
                        Box(
                            modifier = Modifier
                                .windowInsetsPadding(WindowInsets.statusBars)
                        ) {
                            TopBar(navController = navController)
                            DebugButton()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (!isConnected.value) { NetworkLoss() }
                        else { NavigationStack(navController = navController) }
                    }
                }
            }
        }
    }
}
