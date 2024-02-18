package com.mato.gitlab

import okhttp3.logging.HttpLoggingInterceptor

object GitlabContext {

    lateinit var baseUrl: String

    lateinit var token: String

    var loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Config
     *
     * @param baseUrl base url for api request
     * @param token gitlab token for api request header, must be provided
     * @param others other configurations, e.g. logging
     * @receiver GitlabContext, token, url and other configurations management
     */
    fun config(
        baseUrl: String,
        token: String,
        others: GitlabContext.() -> Unit = { }
    ) {
        this.baseUrl = baseUrl
        this.token = token
        this.apply(others)
    }
}