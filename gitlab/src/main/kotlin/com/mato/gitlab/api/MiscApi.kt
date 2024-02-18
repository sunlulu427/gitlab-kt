package com.mato.gitlab.api

import com.mato.gitlab.request.FileTemplate
import com.mato.gitlab.request.KeyNamePair
import retrofit2.http.GET
import retrofit2.http.Path

interface MiscApi {

    @GET("templates/gitignores")
    suspend fun getGitignoreTemplates(): Result<List<KeyNamePair>>

    @GET("templates/gitignores/{key}")
    suspend fun getGitignoreTemplate(@Path("key") key: String): Result<FileTemplate>

    @GET("templates/gitlab_ci_ymls")
    suspend fun getGitlabCIYamlTemplates(): Result<List<KeyNamePair>>

    @GET("templates/gitlab_ci_ymls/{key}")
    suspend fun getGitlabCIYamlTemplate(@Path("key") key: String): Result<FileTemplate>
}