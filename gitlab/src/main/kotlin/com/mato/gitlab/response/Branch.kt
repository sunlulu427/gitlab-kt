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
    val id: Int? = null,
    val name: String,
    val pushAccessLevels: List<Access>,
    val mergeAccessLevels: List<Access>,
    val allowForcePush: Boolean? = null,
    val codeOwnerApprovalRequired: Boolean? = null,
    val unprotectAccessLevels: List<Access>? = null,
    val inherited: Boolean? = null
) {
    @Serializable
    data class Access(
        val id: Int? = null,
        val accessLevel: Int,
        val userId: Int? = null,
        val groupId: Int? = null,
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
