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

@Serializable
data class ProtectedBranch(
    val name: String,
    val pushAccessLevels: List<Access>,
    val mergeAccessLevels: List<Access>,
    val allowForcePush: Boolean?,
    val codeOwnerApprovalRequired: Boolean?,
    val inherited: Boolean?
) {
    @Serializable
    data class Access(
        val accessLevel: Int,
        val accessLevelDescription: String,
        val deployKeyId: Int? = null
    )

    enum class AccessLevel(val value: Int) {
        None(0),
        Developer(30),
        Maintainer(40),
        Admin(60)
    }
}
