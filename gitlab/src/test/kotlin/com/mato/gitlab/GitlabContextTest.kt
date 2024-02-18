package com.mato.gitlab

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class GitlabContextTest : BaseTestCase {

    @Test
    fun config() {
        assertNotNull(GitlabContext.token)
        assertNotNull(GitlabContext.baseUrl)
        assertNotNull(GitlabContext.loggingInterceptor)
    }
}