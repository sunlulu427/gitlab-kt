package com.mato.gitlab.api

import com.mato.gitlab.GitlabService
import com.mato.gitlab.response.ProtectedBranch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

/**
 * @author sunlulu.tomato
 * @date 02/19/2024
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BranchApiTest : BaseTestCase {

    private val branchApi = GitlabService.get<BranchApi>()
    private val projectId = TestingEnv.GITLAB_TEST_PROJECT_ID.get()
    private val branchName = TestingEnv.GITLAB_TEST_BRANCH.get()
    private val newBranchName = "new_${branchName}_${System.currentTimeMillis()}"
    private val newBranchRegex = "new_${branchName}_*"

    @Test
    @Order(1)
    fun testGetRepoBranches() = runBlocking {
        val repoBranches = branchApi.getRepoBranches(projectId)
        repoBranches.assertSuccess()
        assertTrue(repoBranches.getOrThrow().isNotEmpty())
    }

    @Test
    @Order(2)
    fun testGetSingleBranchInfo() = runBlocking {
        val branch = branchApi.getSingleBranch(projectId, branchName)
        branch.assertSuccess()
        assertEquals(branch.getOrThrow().name, branchName)
    }

    @Test
    @Order(3)
    fun testCreateReoBranch() = runBlocking {
        val createBranch = branchApi.createRepoBranch(projectId, newBranchName, branchName)
        createBranch.assertSuccess()
        assertEquals(createBranch.getOrThrow().name, newBranchName)
    }


    @Test
    @Order(4)
    fun testGetProtectedBranches() = runBlocking {
        val protectedBranches = branchApi.getProtectedBranches(projectId)
        protectedBranches.assertSuccess()
        assertTrue(protectedBranches.getOrThrow().isNotEmpty())
    }

    @Test
    @Order(5)
    fun testProtectBranch() = runBlocking {
        val protectBranch = branchApi.protectBranches(
            projectId,
            newBranchRegex,
            allowForcePush = true,
            pushAccessLevel = ProtectedBranch.AccessLevel.Developer.value
        )
        protectBranch.assertSuccess()
        assertEquals(protectBranch.getOrThrow().name, newBranchRegex)
    }

    @Test
    @Order(6)
    fun testGetSingleProtectedBranch() = runBlocking {
        val protectedBranch = branchApi.getSingleProtectedBranch(projectId, newBranchName)
        protectedBranch.assertSuccess()
        assertEquals(protectedBranch.getOrThrow().name, newBranchName)
    }

    @Test
    @Order(7)
    fun testUpdateProtectedBranch() = runBlocking {
        val updateProtectedBranch = branchApi.updateProtectedBranch(
            projectId, newBranchName, allowForcePush = false, codeOwnerApprovalRequired = false
        )
        updateProtectedBranch.assertSuccess()
        assertEquals(updateProtectedBranch.getOrThrow().name, newBranchName)
    }

    @Test
    @Order(8)
    fun testUnprotectBranches() = runBlocking {
        val unprotectBranch = branchApi.unprotectBranches(projectId, newBranchName)
        unprotectBranch.assertSuccess()
    }

    @Test
    @Order(9)
    fun testDeleteBranch() = runBlocking {
        val createdBranches = branchApi.getRepoBranches(projectId, regex = newBranchRegex)
        createdBranches.assertSuccess()
        createdBranches.getOrThrow().forEach {
                val delete = branchApi.deleteRepoBranch(projectId, it.name)
                delete.assertSuccess()
            }
    }

    @Test
    @Order(10)
    fun testDeleteMergedBranches() = runBlocking {
        val deleteMergedBranches = branchApi.deleteMergedBranches(projectId)
        deleteMergedBranches.assertSuccess()
    }
}