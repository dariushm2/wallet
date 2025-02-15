package com.darius.wallet.ext

import timber.log.Timber

inline fun <reified T> Any?.asType() = try {
    this as? T
} catch (e: ClassCastException) {
    Timber.e(e)
    null
}
