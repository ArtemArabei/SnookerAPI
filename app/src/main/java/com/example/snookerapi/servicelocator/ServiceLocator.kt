package com.example.snookerapi.servicelocator

object ServiceLocator {

    private val apiDataSource by lazy {
        ApiDataSource()
    }

    fun provideApiDataSource(): ApiDataSource = apiDataSource

}

