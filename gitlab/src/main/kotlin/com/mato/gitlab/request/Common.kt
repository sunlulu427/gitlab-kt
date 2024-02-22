package com.mato.gitlab.request

import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
@Serializable
data class DryRunResult(
    val dryRun: String
) {
    val isSuccess get() = dryRun == "success"
}