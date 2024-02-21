package com.mato.gitlab.api

import com.mato.gitlab.response.Branch
import retrofit2.http.GET
import retrofit2.http.Path

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
     */
    @GET("projects/{id}/repository/branches")
    suspend fun getRepoBranches(@Path("id") id: String): Result<List<Branch>>

    /**
     * Get single branch
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user
     * @param branch URL-encoded name of the branch.
     * @return a single project repository branch
     */
    @GET("projects/{id}/repository/branches/{branch}")
    suspend fun getSingleBranch(@Path("id") id: String, @Path("branch") branch: String): Result<Branch>
}