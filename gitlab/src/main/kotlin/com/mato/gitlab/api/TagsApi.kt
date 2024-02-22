package com.mato.gitlab.api

import com.mato.gitlab.response.Tag
import com.mato.gitlab.response.X509Signature
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
interface TagsApi {

    @GET("projects/{id}/repository/tags")
    suspend fun listRepositoryTags(
        @Path("id") id: String,
        @Query("order_by") orderBy: String? = null,
        @Query("sort") sort: String? = null,
        @Query("search") search: String? = null
    ): Result<List<Tag>>


    @GET("projects/{id}/repository/tags/{tag_name}")
    suspend fun getSingleRepositoryTag(
        @Path("id") id: String,
        @Path("tag_name") tagName: String
    ): Result<Tag>

    @POST("projects/{id}/repository/tags")
    suspend fun createNewTag(
        @Path("id") id: String,
        @Query("tag_name") tagName: String,
        @Query("ref") ref: String,
        @Query("message") message: String? = null
    ): Result<Tag>

    @DELETE("projects/{id}/repository/tags/{tag_name}")
    suspend fun deleteTag(
        @Path("id") id: String,
        @Path("tag_name") tagName: String
    ): Result<Unit>

    @GET("projects/{id}/repository/tags/{tag_name}/signature")
    suspend fun getX509SignatureOfTag(
        @Path("id") id: String,
        @Path("tag_name") tagName: String
    ): Result<X509Signature>
}