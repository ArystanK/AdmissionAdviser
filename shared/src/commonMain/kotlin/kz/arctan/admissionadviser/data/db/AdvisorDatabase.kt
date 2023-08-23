package kz.arctan.admissionadviser.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kz.arctan.Database
import kz.arctan.sqldelight.chat.data.Answer
import kz.arctan.sqldelight.chat.data.Message
import org.koin.java.KoinJavaComponent.inject

class AdvisorDatabase {
    private val database: Database by inject(Database::class.java)

    suspend fun getAllAnswers(): Flow<List<Answer>> = coroutineScope {
        database.answerQueries.getAllAnswers().asFlow().mapToList(coroutineContext)
    }

    suspend fun getAllMessages(): Flow<List<Message>> = coroutineScope {
        database.messageQueries.getAllMessages().asFlow().mapToList(coroutineContext)
    }
}