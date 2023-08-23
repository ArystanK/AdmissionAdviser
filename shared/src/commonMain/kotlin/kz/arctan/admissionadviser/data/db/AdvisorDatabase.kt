package kz.arctan.admissionadviser.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kz.arctan.Database
import kz.arctan.sqldelight.chat.data.Answer
import kz.arctan.sqldelight.chat.data.Message

class AdvisorDatabase(private val database: Database) {

    suspend fun getAllAnswers(): Flow<List<Answer>> = coroutineScope {
        database.answerQueries.getAllAnswers().asFlow().mapToList(coroutineContext)
    }

    suspend fun getAllMessages(): Flow<List<Message>> = coroutineScope {
        database.messageQueries.getAllMessages().asFlow().mapToList(coroutineContext)
    }

    suspend fun sentMessage(message: String, fromAi: Boolean) = coroutineScope {
        database.messageQueries.sentMessage(fromAi.toLong(), message)
    }
}