package kz.arctan.admissionadviser.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kz.arctan.admissionadviser.data.remote.dto.MessageRequestDto

class MessageService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

    }

    suspend fun sendMessage(sendMessageDto: MessageRequestDto) =
        client.post {
            url {
                protocol = URLProtocol.HTTPS
                host = "api-inference.huggingface.co"
                path("models/Alpi157/Final_advisor")
                headers { 
                    append(HttpHeaders.Authorization, "Bearer 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")
                }
                contentType(ContentType.Application.Json)
                setBody(sendMessageDto)
            }
        }.also { println(it.bodyAsText()) }
}