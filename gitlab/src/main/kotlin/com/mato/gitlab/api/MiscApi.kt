package com.mato.gitlab.api

import com.mato.gitlab.request.GitignoreTemplate
import retrofit2.http.GET
import retrofit2.http.Path

interface MiscApi {

    @GET("/templates/gitignores")
    suspend fun getGitIgnoreTemplates(): Result<List<GitignoreTemplate>>

    @GET("/templates/gitignores/{key}")
    suspend fun getGitIgnoreTemplate(@Path("key") key: String): Result<GitignoreTemplate>
}