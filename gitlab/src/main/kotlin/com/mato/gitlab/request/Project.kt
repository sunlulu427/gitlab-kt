package com.mato.gitlab.request

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
enum class OrderBy {
    ID,
    NAME,
    PATH,
    CREATED_AT,
    UPDATED_AT,
    LAST_ACTIVITY_AT,
    SIMILARITY,
    REPOSITORY_SIZE,
    STORAGE_SIZE,
    PACKAGES_SIZE,
    WIKI_SIZE
}

enum class Visibility {
    PUBLIC, INTERNAL, PRIVATE
}