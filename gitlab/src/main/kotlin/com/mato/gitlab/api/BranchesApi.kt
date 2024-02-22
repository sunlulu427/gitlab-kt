package com.mato.gitlab.api

import com.mato.gitlab.request.ProtectBranchOption
import com.mato.gitlab.response.Branch
import com.mato.gitlab.response.ProtectedBranch
import retrofit2.http.*

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 * @sample com.mato.gitlab.api.BranchesApiTest
 */
interface BranchesApi {
    /**
     * Get repo branches
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user.
     * @param search Return list of branches containing the search string. You can use ^term to find branches that
     *               begin with term, and term$ to find branches that end with term.
     * @param regex Return list of branches with names matching a re2 regular expression.
     * @return a list of repository branches from a project, sorted by name alphabetically.
     */
    @GET("projects/{id}/repository/branches")
    suspend fun getRepoBranches(
        @Path("id") id: String,
        @Query("search") search: String? = null,
        @Query("regex") regex: String? = null
    ): Result<List<Branch>>

    /**
     * Get single branch
     *
     * @param id ID or URL-encoded path of the project owned by the authenticated user
     * @param branch URL-encoded name of the branch.
     * @return a single project repository branch
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
     */
    @DELETE("projects/{id}/repository/merged_branches")
    suspend fun deleteMergedBranches(@Path("id") id: String): Result<Unit>

    /**
     * Gets a list of protected branches from a project as they are defined in the UI. If a wildcard is set,
     * it is returned instead of the exact name of the branches that match that wildcard.
     *
     * @param id The ID or URL-encoded path of the project owned by the authenticated user
     * @param search Name or part of the name of protected branches to be searched for
     * @return A list of protected branches
     */
    @GET("projects/{id}/protected_branches")
    suspend fun getProtectedBranches(
        @Path("id") id: String,
        @Query("search") search: String? = null
    ): Result<List<ProtectedBranch>>

    /**
     * Get single protected branch
     *
     * @param id The ID or URL-encoded path of the project owned by the authenticated user
     * @param name The name of the branch or wildcard
     * @return A single protected branch or wildcard protected branch
     */
    @GET("projects/{id}/protected_branches/{name}")
    suspend fun getSingleProtectedBranch(
        @Path("id") id: String,
        @Path("name") name: String
    ): Result<ProtectedBranch>

    @POST("projects/{id}/protected_branches")
    suspend fun protectBranches(
        @Path("id") id: String,
        @Query("name") name: String,
        @Query("allow_force_push") allowForcePush: Boolean? = null,
        @Query("code_owner_approval_required") codeOwnerApprovalRequired: Boolean? = null,
        @Query("push_access_level") pushAccessLevel: Int? = null,
        @Query("merge_access_level") mergeAccessLevel: Int? = null,
        @Query("unprotect_access_level") unprotectAccessLevel: Int? = null,
        @Body option: ProtectBranchOption = ProtectBranchOption()
    ): Result<ProtectedBranch>

    @PATCH("projects/{id}/protected_branches/{name}")
    suspend fun updateProtectedBranch(
        @Path("id") id: String,
        @Path("name") name: String,
        @Query("allow_force_push") allowForcePush: Boolean? = null,
        @Query("code_owner_approval_required") codeOwnerApprovalRequired: Boolean? = null,
        @Body option: ProtectBranchOption = ProtectBranchOption()
    ): Result<ProtectedBranch>

    /**
     * Un protects the given protected branch or wildcard protected branch.
     *
     * @param id The ID or URL-encoded path of the project owned by the authenticated user
     * @param name The name of the branch or wildcard
     * @return nothing
     */
    @DELETE("projects/{id}/protected_branches/{name}")
    suspend fun unprotectBranches(
        @Path("id") id: String,
        @Path("name") name: String
    ): Result<Unit>
}