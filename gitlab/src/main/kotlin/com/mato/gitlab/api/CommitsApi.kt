package com.mato.gitlab.api

import com.mato.gitlab.response.Commit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
interface CommitsApi {
    @GET("projects/{id}/repository/commits")
    suspend fun listRepositoryCommits(
        @Path("id") id: String,
        @Query("ref_name") refName: String? = null,
        @Query("since") since: String? = null,
        @Query("until") until: String? = null,
        @Query("path") path: String? = null,
        @Query("author") author: String? = null,
        @Query("all") all: Boolean? = null,
        @Query("with_stats") withStats: Boolean? = null,
        @Query("first_parent") fistParent: String? = null,
        @Query("order") order: String? = null,
        @Query("trailers") trailers: Boolean? = null
    ): Result<List<Commit>>

    @GET("projects/{id}/repository/commits/{sha}")
    suspend fun getSingleCommit(
        @Path("id") id: String,
        @Path("sha") sha: String,
        @Query("stats") stats: Boolean? = null
    ): Result<Commit>
}