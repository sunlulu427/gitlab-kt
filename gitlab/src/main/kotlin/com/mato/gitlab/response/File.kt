package com.mato.gitlab.response

import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
@Serializable
data class FileOperatorResponse(
    val filePath: String,
    val branch: String
)