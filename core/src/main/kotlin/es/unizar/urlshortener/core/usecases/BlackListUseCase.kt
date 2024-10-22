package es.unizar.urlshortener.core.usecases

import es.unizar.urlshortener.core.*

interface BlacklistUseCase{
    fun isUrlBlacklisted(url:String):Boolean
}

class BlacklistUseCaseImpl(): BlacklistUseCase {

    private val blacklistedUrls = listOf(
        "example.com",
        "blockedsite.com",
        "maliciouswebsite.net"
    )

    // Método que verifica si una URL está en la blacklist
    override fun isUrlBlacklisted(url: String): Boolean {
        return blacklistedUrls.any { url.contains(it) }
    }
}