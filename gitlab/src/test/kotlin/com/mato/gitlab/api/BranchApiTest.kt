package com.mato.gitlab.api

import com.mato.gitlab.GitlabService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * @author sunlulu.tomato
 * @date 02/19/2024
 */
class BranchApiTest : BaseTestCase {

    private val branchApi = GitlabService.get<BranchApi>()
    private val projectId = TestingEnv.GITLAB_TEST_PROJECT_ID.get()
    private val branchName = TestingEnv.GITLAB_TEST_BRANCH.get()

    @Test
    fun testGetRepoBranches() = runBlocking {
        val result = branchApi.getRepoBranches(projectId)
        assertTrue(result.isSuccess)
        val data = result.getOrThrow()
        assertTrue(data.isNotEmpty())
        for (branch in data) {
            assertTrue(branch.name.isNotEmpty())
        }
    }

    @Test
    fun testGetSingleBranch() = runBlocking {
        val result = branchApi.getSingleBranch(projectId, branchName)
        assertTrue(result.isSuccess)
        val branch = result.getOrThrow()
        assertEquals(branch.name, branchName)
        assertTrue(branch.commit.id.isNotEmpty())
    }
}