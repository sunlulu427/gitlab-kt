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
    val createdAt: String,
    val parentIds: List<String> = emptyList(),
    val title: String,
    val message: String,
    val authorName: String,
    val authorEmail: String,
    val authoredDate: String,
    val committerName: String,
    val committerEmail: String,
    val committedDate: String,
//    val trailers: Map<String, String> = emptyMap(),
    val webUrl: String = ""
)