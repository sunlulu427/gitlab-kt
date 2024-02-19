package com.mato.gitlab.api

/**
 * @author sunlulu.tomato
 * @date 02/19/2024
 */
enum class TestingEnv {
    GITLAB_BASE_URL,
    GITLAB_TOKEN,
    GITLAB_TEST_EMAIL,
    GITLAB_TEST_PROJECT_ID;

    fun get(): String = System.getenv(name)
}
