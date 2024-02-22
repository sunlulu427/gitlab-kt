package com.mato.gitlab.api

import com.mato.gitlab.GitlabService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
class ProjectsApiTest : BaseTestCase {

    private val projectApi = GitlabService.get<ProjectsApi>()

    @Test
    fun testGetProjects() = runBlocking {
        val projects = projectApi.getAllProjects()
        projects.assertSuccess()
    }
}