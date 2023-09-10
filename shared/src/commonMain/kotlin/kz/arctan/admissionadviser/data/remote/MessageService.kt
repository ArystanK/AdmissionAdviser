package kz.arctan.admissionadviser.data.remote

interface MessageService {
    suspend fun sendMessage(question: String): String
}