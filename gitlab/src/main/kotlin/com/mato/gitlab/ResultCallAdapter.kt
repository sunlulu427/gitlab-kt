package com.mato.gitlab

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 */
class ResultCallAdapter<R>(private val responseType: Type) : CallAdapter<R, Call<Result<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<Result<R>> {
        return ResultCall(call, responseType)
    }

    private class ResultCall<R>(private val delegate: Call<R>, private val responseType: Type) : Call<Result<R>> {
        private fun Response<R>.asResult(): Response<Result<R>> {
            val result = if (isSuccessful) {
                val body = body()
                if (body != null) {
                    Result.success(body)
                } else {
                    when (responseType) {
                        Unit::class.java -> Result.success(Unit) as Result<R>
                        else -> Result.failure(NullPointerException("Response body is null"))
                    }
                }
            } else {
                Result.failure(RuntimeException("Response error: ${code()}"))
            }
            return Response.success(result)
        }

        override fun enqueue(callback: Callback<Result<R>>) {
            delegate.enqueue(object : Callback<R> {
                override fun onResponse(call: Call<R>, response: Response<R>) {
                    val result = response.asResult()
                    callback.onResponse(this@ResultCall, result)
                }

                override fun onFailure(call: Call<R>, t: Throwable) {
                    callback.onResponse(this@ResultCall, Response.success(Result.failure(t)))
                }
            })
        }


        override fun execute(): Response<Result<R>> {
            val response = delegate.execute()
            return response.asResult()
        }

        override fun clone(): Call<Result<R>> {
            return ResultCall(delegate.clone(), responseType)
        }

        override fun isExecuted(): Boolean {
            return delegate.isExecuted
        }

        override fun cancel() {
            delegate.cancel()
        }

        override fun isCanceled(): Boolean {
            return delegate.isCanceled
        }

        override fun request(): Request {
            return delegate.request()
        }

        override fun timeout(): Timeout {
            return delegate.timeout()
        }
    }
}