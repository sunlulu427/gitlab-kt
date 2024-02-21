package com.mato.gitlab.api

import com.mato.gitlab.GitlabContext
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll

interface BaseTestCase {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            GitlabContext.config(
                baseUrl = TestingEnv.GITLAB_BASE_URL.get(),
                token = TestingEnv.GITLAB_TOKEN.get()
            ) {
                loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger(::println)).also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }
            }
        }
    }

    fun <T> Result<T>.assertSuccess() {
        Assertions.assertTrue(this.isSuccess, this.exceptionOrNull()?.message)
    }
}