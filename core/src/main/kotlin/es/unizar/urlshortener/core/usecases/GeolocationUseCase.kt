package es.unizar.urlshortener.core.usecases

import es.unizar.urlshortener.core.GeolocationData

/**
 * [GeolocationUseCase] defines the contract for obtaining geographical information
 * based on the client's IP address.
 */
interface GeolocationUseCase {
    fun getGeolocation(ip: String): GeolocationData?
}
