package com.mato.gitlab.api

import com.mato.gitlab.response.Branch
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 */
interface BranchApi {
    @GET("projects/{id}/repository/branches")
    suspend fun getRepoBranches(@Path("id") id: String): Result<List<Branch>>
}