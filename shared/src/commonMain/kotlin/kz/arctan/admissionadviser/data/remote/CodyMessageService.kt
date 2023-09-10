package kz.arctan.admissionadviser.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kz.arctan.admissionadviser.data.remote.dto.CodyMessageRequestDto
import kz.arctan.admissionadviser.data.remote.dto.CodyMessageResponseDto


class CodyMessageService : MessageService {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000_000
        }
    }

    override suspend fun sendMessage(question: String): String {
        return client.post {
            url {
                protocol = URLProtocol.HTTPS
                host = "getcody.ai"
                path("api/v1/messages")
                headers {
                    append(HttpHeaders.Authorization, "Bearer kxW4moTZASkArLSB7VkC8l5mJz0d3g4CmXxWkqap")
                }
                contentType(ContentType.Application.Json)
                setBody(
                    CodyMessageRequestDto(
                        content = question,
                        conversationId = "wMvbmqn8rbYA"
                    )
                )
            }
        }.body<CodyMessageResponseDto>().answer.content
    }
}