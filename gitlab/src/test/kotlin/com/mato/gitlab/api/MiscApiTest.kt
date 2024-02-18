package com.mato.gitlab.api

import com.mato.gitlab.BaseTestCase
import com.mato.gitlab.GitlabService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 */
class MiscApiTest : BaseTestCase {

    private val miscApi = GitlabService.get(MiscApi::class.java)

    @Test
    fun testGetGitignoreTemplates(): Unit = runBlocking {
        val result = miscApi.getGitignoreTemplates()
        assert(result.isSuccess)
        val data = result.getOrThrow()
        assert(data.isNotEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["C++", "C", "Android", "CMake"])
    fun testGetGitignoreTemplate(key: String): Unit = runBlocking {
        val result = miscApi.getGitignoreTemplate(key)
        assert(result.isSuccess)
        val data = result.getOrThrow()
        assertEquals(data.name, key)
        assertNotNull(data.content)
        assert(data.content.isNotEmpty())
    }
}