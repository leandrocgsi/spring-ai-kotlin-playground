package br.com.erudio.controller

import br.com.erudio.service.TranscriptionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
class TranscriptionController(private val service: TranscriptionService) {

    @PostMapping("/ai/transcribe")
    fun transcribeAudio(@RequestParam("file") file: MultipartFile): ResponseEntity<String?> {
        try {
            val transcription = service.transcribeAudio(file)
            return ResponseEntity.ok<String?>(transcription)
        } catch (e: IOException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body<String?>("Error processing the audio file: " + e.message)
        }
    }
}
