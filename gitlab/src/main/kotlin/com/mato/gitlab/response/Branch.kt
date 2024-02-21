package com.mato.gitlab.response

import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/18/2024
 */
@Serializable
data class Branch(
    val name: String,
    val merged: Boolean,
    val protected: Boolean,
    val default: Boolean,
    val developersCanPush: Boolean,
    val developersCanMerge: Boolean,
    val canPush: Boolean,
    val webUrl: String = "",
    val commit: Commit
)