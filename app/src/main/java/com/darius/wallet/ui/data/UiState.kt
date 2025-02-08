package com.darius.wallet.ui.data

import com.darius.wallet.data.UiData

sealed class UiState {
    data object Loading : UiState()
    data class Success<T : UiData>(val recipes: List<T>) : UiState()
    data class Error(val message: String?) : UiState()
}