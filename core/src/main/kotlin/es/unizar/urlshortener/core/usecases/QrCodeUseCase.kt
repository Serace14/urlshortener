package es.unizar.urlshortener.core.usecases

import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

// Interfaz que define el contrato para la generación de códigos QR
interface QrCodeUseCase {
    fun generateQrCode(url: String): ByteArray
}

//@Service
class QrCodeUseCaseImpl : QrCodeUseCase {

    // Implementación del método para generar un código QR
    override fun generateQrCode(url: String): ByteArray {
        val qrCodeWriter = QRCodeWriter()
        return try {
            // Generar la matriz del código QR
            val bitMatrix: BitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200)

            // Convertir la matriz en una imagen
            val image: BufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix)

            // Escribir la imagen a un stream de bytes
            val outputStream = ByteArrayOutputStream()
            ImageIO.write(image, "PNG", outputStream)

            // Devolver el array de bytes
            outputStream.toByteArray()
        } catch (e: WriterException) {
            e.printStackTrace()
            ByteArray(0) // En caso de error, devolver un array vacío
        }
    }
}
