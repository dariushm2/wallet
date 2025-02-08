package com.darius.wallet.ext

@Suppress("UNCHECK_CAST")
fun <T> Any?.asType() = try {
    this as? T
} catch (e: ClassCastException) {
    e.printStackTrace()
    null
}