package com.mato.gitlab.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AccessOption {
    @Serializable
    data class UserId(val userId: Int) : AccessOption

    @Serializable
    data class GroupId(val groupId: Int) : AccessOption

    @Serializable
    data class AccessLevel(val accessLevel: Int) : AccessOption

    @Serializable
    data class Id(val id: Int, @SerialName("_destroy") val destroy: Boolean) : AccessOption
}

/**
 * Protect branch request body
 *
 * @property allowedToMerge Array of merge access levels, with each described by a hash of the form {user_id: integer},
 *                          {group_id: integer}, or {access_level: integer}. Premium and Ultimate only.
 * @property allowedToPush Array of push access levels, with each described by a hash of the form {user_id: integer},
 *                         {group_id: integer}, or {access_level: integer}. Premium and Ultimate only.
 * @property allowedToUnprotect Array of unprotect access levels, with each described by a hash of the form
 *                              {user_id: integer}, {group_id: integer}, or {access_level: integer}. The access level
 *                              No access is not available for this field. Premium and Ultimate only.
 * @constructor Create empty Protect branch request
 */
@Serializable
data class ProtectBranchOption(
    val allowedToMerge: List<AccessOption>? = null,
    val allowedToPush: List<AccessOption>? = null,
    val allowedToUnprotect: List<AccessOption>? = null,
)