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
        result.assertTrue()
        val data = result.getOrThrow()
        assertTrue(data.isNotEmpty())
        for (branch in data) {
            assertTrue(branch.name.isNotEmpty())
        }
    }

    @Test
    fun testGetSingleBranch() = runBlocking {
        val result = branchApi.getSingleBranch(projectId, branchName)
        result.assertTrue()
        val branch = result.getOrThrow()
        assertEquals(branch.name, branchName)
        assertTrue(branch.commit.id.isNotEmpty())
    }

    @Test
    fun testCreateBranch() = runBlocking {
        val newBranchName = "new_${branchName}_${System.currentTimeMillis()}"
        val result = branchApi.createRepoBranch(
            id = projectId,
            branch = newBranchName,
            ref = branchName
        )
        result.assertTrue()
        val branch = result.getOrThrow()
        assertEquals(branch.name, newBranchName)
    }

    @Test
    fun testDeleteBranch() = runBlocking {
        val newBranchName = "new_${branchName}_${System.currentTimeMillis()}"
        val newBranch = branchApi.createRepoBranch(projectId, newBranchName, branchName).getOrThrow()
        val result = branchApi.deleteRepoBranch(projectId, newBranch.name)
        result.assertTrue()
    }

    @Test
    fun testDeleteMergedBranches() = runBlocking {
        branchApi.deleteMergedBranches(projectId)
            .assertTrue()
    }

    @Test
    fun testGetProtectedBranches() = runBlocking {
        val result = branchApi.getProtectedBranches(projectId)
        result.assertTrue()
        val branches = result.getOrThrow()
        assertTrue(branches.isNotEmpty())
    }

    @Test
    fun testGetSingleProtectedBranch() = runBlocking {
        val result = branchApi.getSingleProtectedBranch(id = projectId, name = branchName)
        result.assertTrue()
        val branch = result.getOrThrow()
        assertEquals(branch.name, branchName)
    }
}