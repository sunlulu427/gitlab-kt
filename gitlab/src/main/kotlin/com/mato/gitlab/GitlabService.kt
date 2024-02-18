package com.mato.gitlab

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

object GitlabService {
    private val client = OkHttpClient.Builder()
        .addInterceptor {
            val builder = it.request().newBuilder()
            // add headers
            builder.addHeader("Authorization", "Bearer ${GitlabContext.token}")
            it.proceed(builder.build())
        }
        .addInterceptor(GitlabContext.loggingInterceptor)
        .build()

    private val callAdapterFactory = object : CallAdapter.Factory() {
        override fun get(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
            return null
        }
    }

    private val mediaType = MediaType.get("application/json")

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(GitlabContext.baseUrl)
        .addConverterFactory(Json.asConverterFactory(mediaType))
        .addCallAdapterFactory(callAdapterFactory)
        .build()

    private val serviceCollection = mutableMapOf<Class<*>, Any>()

    fun <T : Any> get(clazz: Class<T>): T = if (clazz in serviceCollection) {
        @Suppress("UNCHECKED_CAST")
        serviceCollection[clazz] as T
    } else {
        retrofit.create(clazz).also {
            serviceCollection[clazz] = it
        }
    }
}