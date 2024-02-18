package com.mato.gitlab.request

import kotlinx.serialization.Serializable

@Serializable
data class GitignoreTemplate(
    val key: String,
    val name: String
)
