package com.mato.gitlab

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object GitlabService {
    private val client = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
            val builder = it.request().newBuilder()
            // add headers
            builder.addHeader("PRIVATE-TOKEN", GitlabContext.token)
            builder.addHeader("Content-Type", "application/json")
            builder.addHeader("Accept", "application/json")
            it.proceed(builder.build())
        }
        .addInterceptor(GitlabContext.loggingInterceptor)
        .build()

    private val callAdapterFactory = object : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            // Get the actual type inside Call<Result<T>>
            val typeInsideResult = getParameterUpperBound(0, returnType as ParameterizedType)
            val rawType = getRawType(typeInsideResult)
            if (rawType != Result::class.java) {
                return null
            }

            // Get the actual type inside Result<T>
            val typeInsideCall = getParameterUpperBound(0, typeInsideResult as ParameterizedType)
            return ResultCallAdapter<Any>(typeInsideCall)
        }
    }

    private val mediaType = MediaType.get("application/json")

    val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(GitlabContext.baseUrl)
        .addConverterFactory(Json.asConverterFactory(mediaType))
        .addCallAdapterFactory(callAdapterFactory)
        .build()

    val serviceCollection = mutableMapOf<Class<*>, Any>()

    inline fun <reified T : Any> get(): T = if (T::class.java in serviceCollection) {
        serviceCollection[T::class.java] as T
    } else {
        retrofit.create(T::class.java).also {
            serviceCollection[T::class.java] = it
        }
    }
}