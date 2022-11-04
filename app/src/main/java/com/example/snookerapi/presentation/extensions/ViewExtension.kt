package com.example.snookerapi.presentation.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getProperValue(valueChecker: (input: Int?) -> String?): Int? {
    val resultValue = editText?.text?.toString()
        ?.ifBlank {
            null
        }?.toInt()
    error = valueChecker(resultValue)
    return when (error) {
        null -> resultValue
        else -> null
    }
}