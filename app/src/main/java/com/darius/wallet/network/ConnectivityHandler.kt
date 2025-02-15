package com.darius.wallet.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.darius.wallet.ext.asType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectivityHandler @Inject constructor(
    context: Context,
) {

    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> = _isConnected

    init {
        val networkManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            ?.asType<ConnectivityManager>()

        CoroutineScope(Dispatchers.Default).launch {
            _isConnected.emit(networkManager?.activeNetwork != null)
        }

        networkManager?.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                CoroutineScope(Dispatchers.Default).launch {
                    _isConnected.emit(true)
                }
                Log.e("network", "available")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                CoroutineScope(Dispatchers.Default).launch {
                    _isConnected.emit(false)
                }
                Log.e("network", "lost")
            }
        })
    }
}
