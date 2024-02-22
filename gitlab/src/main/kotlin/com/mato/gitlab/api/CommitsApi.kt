package com.mato.gitlab.api

import com.mato.gitlab.request.DryRunResult
import com.mato.gitlab.response.CommitOperatorResult
import com.mato.gitlab.response.Commit
import com.mato.gitlab.response.CommitSequence
import com.mato.gitlab.response.Reference
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("projects/{id}/repository/commits/{sha}/refs")
    suspend fun getRefsCommitPushTo(
        @Path("id") id: String,
        @Path("sha") sha: String,
        @Query("type") type: String
    ): Result<List<Reference>>

    @GET("projects/{id}/repository/commits/{sha}/sequence")
    suspend fun getSequenceOfCommit(
        @Path("id") id: String,
        @Path("sha") sha: String,
        @Query("first_parent") firstParent: Boolean? = null
    ): Result<CommitSequence>

    @POST("projects/{id}/repository/commits/{sha}/cherry_pick")
    suspend fun cherryPickCommit(
        @Path("id") id: String,
        @Path("sha") sha: String,
        @Field("branch") branch: String,
        @Field("message") message: String? = null
    ): Result<CommitOperatorResult>

    @POST("projects/{id}/repository/commits/{sha}/cherry_pick")
    suspend fun cherryPickCommitDryRun(
        @Path("id") id: String,
        @Path("sha") sha: String,
        @Field("branch") branch: String,
        @Field("message") message: String? = null,
        @Field("dry_run") dryRun: Boolean = true
    ): Result<DryRunResult>

    @POST("projects/{id}/repository/commits/{sha}/revert")
    suspend fun revertCommit(
        @Path("id") id: String,
        @Path("sha") sha: String,
        @Field("branch") branch: String
    ): Result<CommitOperatorResult>

    @POST("projects/{id}/repository/commits/{sha}/revert")
    suspend fun revertCommitDryRun(
        @Path("id") id: String,
        @Path("sha") sha: String,
        @Field("branch") branch: String
    ): Result<DryRunResult>
}