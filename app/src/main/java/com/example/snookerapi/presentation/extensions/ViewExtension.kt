package com.example.snookerapi.presentation.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getYearOrSetError(): Int? {
    return editText?.text?.toString()
        ?.ifBlank {
            error = "Field is empty"
            null
        }?.toInt()
}