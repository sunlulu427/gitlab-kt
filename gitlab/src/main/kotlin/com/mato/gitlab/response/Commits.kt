package com.mato.gitlab.response

import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 */
@Serializable
data class Commit(
    val id: String,
    val shortId: String,
    val title: String,
    val authorName: String
)