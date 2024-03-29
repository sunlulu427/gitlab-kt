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
    val parentIds: List<String>?,
    val title: String,
    val message: String,
    val authorName: String,
    val authorEmail: String,
    val authoredDate: String,
    val committerName: String,
    val committerEmail: String,
    val committedDate: String,
    val webUrl: String = "",
    val trailers: Map<String, String>? = null,
    val extendedTrailers: Map<String, List<String>>? = null
)

@Serializable
data class Reference(
    val type: String,
    val name: String
)

@Serializable
data class CommitSequence(
    val count: Int
)

@Serializable
data class CommitOperatorResult(
    val id: String,
    val message: String,
    val shortId: String,
    val authorName: String,
    val authorEmail: String,
    val authoredDate: String,
    val committerName: String,
    val committerEmail: String,
    val title: String,
    val webUrl: String = "",
    val parentIds: List<String>? = null,
    val errorCode: String? = null,
)