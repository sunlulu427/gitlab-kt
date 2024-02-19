package com.mato.gitlab.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 */
@Serializable
data class Commit(
    val id: String,
    @SerialName("short_id") val shortId: String,
    val title: String,
    @SerialName("author_name") val authorName: String
)