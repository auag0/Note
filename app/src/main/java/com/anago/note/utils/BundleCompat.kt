package com.anago.note.utils

import android.os.Build
import android.os.Bundle
import java.io.Serializable

object BundleCompat {
    inline fun <reified T : Serializable> Bundle.getCSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            getSerializable(key) as? T?
        }
    }
}