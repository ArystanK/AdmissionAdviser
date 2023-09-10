package kz.arctan.admissionadviser.data.remote

import io.ktor.client.request.*
import io.ktor.http.*

//class HuggingFaceMessageService {
//
//    suspend fun sendMessage(sendMessageDto: MessageRequestDto) =
//        client.post {
//            url {
//                protocol = URLProtocol.HTTPS
//                host = "api-inference.huggingface.co"
//                path("models/Alpi157/Final_advisor")
//                headers {
//                    append(HttpHeaders.Authorization, "Bearer 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")
//                }
//                contentType(ContentType.Application.Json)
//                setBody(sendMessageDto)
//            }
//        }
//}