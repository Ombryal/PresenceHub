package com.ombryal.presencehub.security

object SignatureVerifier {

    fun verifySignature(
        pluginData: ByteArray,
        signature: ByteArray,
        publicKeyPem: String
    ): Boolean {
        // Ph for now 
        // Later gonna use this for real public key verificationn
        return pluginData.isNotEmpty() && signature.isNotEmpty() && publicKeyPem.isNotBlank()
    }
}
