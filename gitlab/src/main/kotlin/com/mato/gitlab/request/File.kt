package com.mato.gitlab.request

import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */

@Serializable
data class FileOperatorRequest(
    val branch: String,
    val commitMessage: String,
    val content: String,
    val filePath: String,
    val id: String,
    val authorEmail: String? = null,
    val authorName: String? = null,
    val encoding: String? = null,
    val executeFilemode: Boolean? = null,
    val startBranch: String? = null,
    val lastCommitId: String? = null
)