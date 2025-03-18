package com.example.hw61.di

import com.example.hw61.data.datasource.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        provideRetrofit(okHttpClient = get())
    }

    single {
        provideApiService(retrofit = get())
    }

    single {
        provideOkHttpClient(interceptor = get())
    }

    single {
        provideLoggingInterceptor()
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl( "https://v6.exchangerate-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}