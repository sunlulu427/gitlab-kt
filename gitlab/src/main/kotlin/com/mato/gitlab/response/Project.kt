package com.mato.gitlab.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/19/2024
 */
@Serializable
data class Project(
    val id: Int,
    val description: String?,
    val name: String,
    val nameWithNamespace: String,
    val path: String,
    val pathWithNameSpace: String,
    val createdAt: String,
    val defaultBranch: String,
    val tagList: List<String>,
    val topics: List<String>,
    val sshUrlToRepo: String,
    val httpUrlToRepo: String,
    val webUrl: String,
    val avatarUrl: String,
    val starCount: Int,
    val lastActivityAt: String,
    val namespace: NameSpace,
    @SerialName("_links") val links: Links,
) {
    @Serializable
    data class NameSpace(
        val id: Int,
        val name: String,
        val path: String,
        val kind: String,
        val fullPath: String,
        val parentId: String?,
        val avatarUrl: String?,
        val webUrl: String
    )

    @Serializable
    data class Links(
        val self: String,
        val issues: String,
        val mergeRequests: String,
        val repoBranches: String,
        val labels: String,
        val events: String,
        val members: String,
        val clusterAgents: String
    )
}

