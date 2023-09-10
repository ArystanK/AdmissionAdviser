package kz.arctan.admissionadviser.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CodyMessageRequestDto(
    val content: String,
    @SerialName("conversation_id") val conversationId: String
)