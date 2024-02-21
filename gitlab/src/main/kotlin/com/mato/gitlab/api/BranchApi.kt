package com.mato.gitlab.api

import com.mato.gitlab.response.Branch
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 * @sample com.mato.gitlab.api.BranchApiTest
 */
interface BranchApi {
    /**
     * Get repo branches
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user.
     * @return a list of repository branches from a project, sorted by name alphabetically.
     * @sample com.mato.gitlab.api.BranchApiTest.testGetRepoBranches
     */
    @GET("projects/{id}/repository/branches")
    suspend fun getRepoBranches(@Path("id") id: String): Result<List<Branch>>

    /**
     * Get single branch
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user
     * @param branch URL-encoded name of the branch.
     * @return a single project repository branch
     * @sample com.mato.gitlab.api.BranchApiTest.testGetSingleBranch
     */
    @GET("projects/{id}/repository/branches/{branch}")
    suspend fun getSingleBranch(@Path("id") id: String, @Path("branch") branch: String): Result<Branch>

    /**
     * Create a new branch in the repository
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user.
     * @param branch Name of the branch.
     * @param ref Branch name or commit SHA to create branch from.
     * @return new branch info
     * @sample com.mato.gitlab.api.BranchApiTest.testCreateBranch
     */
    @POST("projects/{id}/repository/branches")
    suspend fun createRepoBranch(
        @Path("id") id: String,
        @Query("branch") branch: String,
        @Query("ref") ref: String
    ): Result<Branch>

    /**
     * Delete a branch from the repository
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user.
     * @param branch Name of the branch.
     * @return response body is empty
     * @sample com.mato.gitlab.api.BranchApiTest.testDeleteBranch
     */
    @DELETE("projects/{id}/repository/branches/{branch}")
    suspend fun deleteRepoBranch(
        @Path("id") id: String,
        @Path("branch") branch: String
    ): Result<Unit>

    /**
     * Delete all branches that are merged into project's default branch.
     * Protected branches are not deleted as part of this operation.
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user.
     * @return empty response
     * @sample com.mato.gitlab.api.BranchApiTest.testDeleteMergedBranches
     */
    @DELETE("projects/{id}/repository/merged_branches")
    suspend fun deleteMergedBranches(@Path("id") id: String): Result<Unit>
}