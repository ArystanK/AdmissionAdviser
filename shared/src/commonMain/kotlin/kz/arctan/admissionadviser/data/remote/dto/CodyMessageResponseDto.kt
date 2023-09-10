package kz.arctan.admissionadviser.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CodyMessageResponseDto(
    @SerialName("data") val answer: Data
)