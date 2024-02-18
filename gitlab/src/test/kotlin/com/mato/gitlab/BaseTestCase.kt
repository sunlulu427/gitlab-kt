package com.mato.gitlab

import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.BeforeAll

interface BaseTestCase {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup(): Unit {
            GitlabContext.config(
                baseUrl = System.getenv("GITLAB_BASE_URL"),
                token = System.getenv("GITLAB_TOKEN")
            ) {
                loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger(::println)).also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }
            }
        }
    }
}