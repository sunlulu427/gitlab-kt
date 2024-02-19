package com.mato.gitlab.api

import com.mato.gitlab.response.Project
import retrofit2.http.GET

/**
 * @author sunlulu.tomato
 * @date 02/19/2024
 */
interface ProjectApi {

    @GET("projects")
    suspend fun getAllProjects(
    ): Result<List<Project>>
}