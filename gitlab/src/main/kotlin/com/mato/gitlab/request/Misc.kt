package com.mato.gitlab.request

import kotlinx.serialization.Serializable


@Serializable
data class KeyNamePair(val key: String, val name: String)

@Serializable
data class FileTemplate(val name: String, val content: String)