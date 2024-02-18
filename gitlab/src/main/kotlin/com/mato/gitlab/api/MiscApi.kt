package com.mato.gitlab.api

import com.mato.gitlab.response.AvatarUrl
import com.mato.gitlab.response.FileTemplate
import com.mato.gitlab.response.KeyNamePair
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MiscApi {

    @GET("templates/gitignores")
    suspend fun getGitignoreTemplates(): Result<List<KeyNamePair>>

    @GET("templates/gitignores/{key}")
    suspend fun getGitignoreTemplate(@Path("key") key: String): Result<FileTemplate>

    @GET("templates/gitlab_ci_ymls")
    suspend fun getGitlabCIYamlTemplates(): Result<List<KeyNamePair>>

    @GET("templates/gitlab_ci_ymls/{key}")
    suspend fun getGitlabCIYamlTemplate(@Path("key") key: String): Result<FileTemplate>

    @GET("avatar")
    suspend fun getAvatarUrl(@Query("email") email: String, @Query("size") size: Int? = null): Result<AvatarUrl>
}