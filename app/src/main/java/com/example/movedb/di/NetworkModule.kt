package com.example.movedb.di

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import com.example.movedb.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideBuilder() }
    single { provideOkHttpClient() }
    single { provideRetrofit(builder = get(), okHttpClient = get()) }
}

internal fun provideOkHttpClient(
): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient.addInterceptor(httpLoggingInterceptor)
    }
    okHttpClient.addInterceptor { chain ->
        return@addInterceptor runBlocking {
            val original = chain.request()
            val originToken = BuildConfig.TOKEN
            val originalRequestBuilder = original.newBuilder()
                .header("Authorization", "Bearer $originToken")

            val originalRequest = originalRequestBuilder.build()
            return@runBlocking chain.proceed(originalRequest)
        }
    }
    return okHttpClient.apply {
        connectTimeout(60L, TimeUnit.SECONDS)
        readTimeout(60L, TimeUnit.SECONDS)
        writeTimeout(60L, TimeUnit.SECONDS)
    }.build()
}

internal fun provideBuilder(): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
}

internal fun provideRetrofit(builder: Retrofit.Builder, okHttpClient: OkHttpClient): Retrofit {
    return builder
        .client(okHttpClient)
        .build()
}