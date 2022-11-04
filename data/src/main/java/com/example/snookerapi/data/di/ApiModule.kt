package com.example.snookerapi.data.di

import com.example.snookerapi.data.BuildConfig
import com.example.snookerapi.data.api.RestCountriesApi
import com.example.snookerapi.data.api.SnookerApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal val apiModule = module {

    single {
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor()
                        .apply { setLevel(HttpLoggingInterceptor.Level.BASIC) })
                }
            }
            .build()
    }

    single(qualifier(ApiQualifier.SNOOKERAPI)) {
        provideRetrofit(BuildConfig.SNOOKER_API_SERVER_URL, get())
    }

    single(qualifier(ApiQualifier.RESTCOUNTRIES)) {
        provideRetrofit(BuildConfig.REST_COUNTRIES_API_SERVER_URL, get())
    }

    single { get<Retrofit>(qualifier(ApiQualifier.SNOOKERAPI)).create<SnookerApi>() }

    single { get<Retrofit>(qualifier(ApiQualifier.RESTCOUNTRIES)).create<RestCountriesApi>() }
}

private fun provideRetrofit(serverUrl: String, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(serverUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}