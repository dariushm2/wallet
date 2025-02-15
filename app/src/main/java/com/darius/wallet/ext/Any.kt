package com.darius.wallet.ext

inline fun <reified T> Any?.asType() = try {
    this as? T
} catch (e: ClassCastException) {
    null
}