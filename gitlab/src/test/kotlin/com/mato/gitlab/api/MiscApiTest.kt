package com.mato.gitlab.api

import com.mato.gitlab.GitlabService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 */
class MiscApiTest : BaseTestCase {

    private val miscApi = GitlabService.get<MiscApi>()

    @Test
    fun testGetGitignoreTemplates(): Unit = runBlocking {
        val result = miscApi.getGitignoreTemplates()
        assumeTrue(result.isSuccess)
        val data = result.getOrThrow()
        assertTrue(data.isNotEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["C++", "C", "Android", "CMake"])
    fun testGetGitignoreTemplate(key: String): Unit = runBlocking {
        val result = miscApi.getGitignoreTemplate(key)
        assertTrue(result.isSuccess)
        val data = result.getOrThrow()
        assertEquals(data.name, key)
        assertNotNull(data.content)
        assertTrue(data.content.isNotEmpty())
    }

    @Test
    fun testGetGitlabCIYamlTemplates(): Unit = runBlocking {
        val result = miscApi.getGitlabCIYamlTemplates()
        assertTrue(result.isSuccess)
        val data = result.getOrThrow()
        assertTrue(data.isNotEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["Android", "C++", "Gradle", "Maven"])
    fun testGetGitlabCIYamlTemplate(key: String): Unit = runBlocking {
        val result = miscApi.getGitlabCIYamlTemplate(key)
        assertTrue(result.isSuccess)
        val data = result.getOrThrow()
        assertEquals(data.name, key)
        assertNotNull(data.content)
        assertTrue(data.content.isNotEmpty())
    }

    @Test
    fun testGetAvatarUrl() = runBlocking {
        val result = miscApi.getAvatarUrl(
            email = TestingEnv.GITLAB_TEST_EMAIL.get(),
            size = 32
        )
        assertTrue(result.isSuccess)
        assertTrue(result.getOrThrow().avatarUrl.isNotEmpty())
    }
}