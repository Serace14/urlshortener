package es.unizar.urlshortener.infrastructure.delivery

import es.unizar.urlshortener.core.usecases.QrCodeUseCase
import es.unizar.urlshortener.core.usecases.RedirectUseCase
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/qr")
class QrCodeController(
    val qrCodeUseCase: QrCodeUseCase,  // Inyectamos el caso de uso del QR
    val redirectUseCase: RedirectUseCase // Para obtener la URL acortada
) {

    /**
     * Endpoint para generar el código QR de una URL acortada.
     *
     * @param id el identificador de la URL acortada
     * @return una imagen PNG del código QR
     */
    @GetMapping("/{id}", produces = [MediaType.IMAGE_PNG_VALUE])
    fun generateQrCode(@PathVariable id: String, request: HttpServletRequest): ResponseEntity<ByteArray> {
        // Lógica para obtener la URL acortada desde el caso de uso existente de redirección
        val shortUrl = redirectUseCase.redirectTo(id).target

        // Generar el código QR utilizando el caso de uso del QR
        val qrCodeImage = qrCodeUseCase.generateQrCode(shortUrl)

        // Devolver la imagen en formato PNG
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeImage)
    }
}
