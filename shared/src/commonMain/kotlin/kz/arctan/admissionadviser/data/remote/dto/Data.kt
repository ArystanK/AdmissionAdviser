package kz.arctan.admissionadviser.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val content: String,
    @SerialName("conversation_id") val conversationId: String,
    @SerialName("created_at") val createdAt: Int,
    @SerialName("failed_responding") val failedResponding: Boolean,
    val flagged: Boolean,
    val id: String,
    val machine: Boolean
)