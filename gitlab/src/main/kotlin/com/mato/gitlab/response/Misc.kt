package com.mato.gitlab.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class KeyNamePair(val key: String, val name: String)

@Serializable
data class FileTemplate(val name: String, val content: String)

@Serializable
data class AvatarUrl(@SerialName("avatar_url") val avatarUrl: String)