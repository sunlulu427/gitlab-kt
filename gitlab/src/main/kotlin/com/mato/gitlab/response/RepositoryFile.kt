package com.mato.gitlab.response

import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
@Serializable
data class RepositoryFile(
    val fileName: String,
    val filePath: String,
    val size: Int,
    val encoding: String,
    val content: String,
    val contentSha256: String,
    val ref: String,
    val blobId: String,
    val commitId: String,
    val lastCommitId: String,
    val executeFilemode: Boolean
)

@Serializable
data class FileBlameInfo(
    val commit: Commit,
    val lines: List<String>
)
