package com.mato.gitlab.response

import kotlinx.serialization.Serializable

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
@Serializable
data class Tag(
    val commit: Commit,
    val release: Release,
    val name: String,
    val target: String,
    val protected: Boolean,
    val message: String = ""
) {
    @Serializable
    data class Release(
        val releaseName: String,
        val description: String = ""
    )
}

@Serializable
data class X509Signature(
    val signatureType: String,
    val verificationStatus: String,
    val x509Certificate: X509Certificate
) {
    @Serializable
    data class X509Certificate(
        val id: Int,
        val subject: String,
        val subjectKeyIdentifier: String,
        val email: String,
        val serialNumber: String,
        val certificateStatus: String,
        val x509Issuer: X509Issuer
    )

    @Serializable
    data class X509Issuer(
        val id: Int,
        val subject: String,
        val subjectKeyIdentifier: String,
        val crlUrl: String
    )
}