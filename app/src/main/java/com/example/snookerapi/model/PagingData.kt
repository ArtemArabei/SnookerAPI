package com.example.snookerapi.model

sealed class PagingData<out T> {
    data class Item<T>(val data: T) : PagingData<T>()

    object Loading : PagingData<Nothing>()
}