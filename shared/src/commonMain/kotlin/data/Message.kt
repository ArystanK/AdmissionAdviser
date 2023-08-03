package data


enum class Source {
    USER, AI
}
data class Message(
    val content: String,
    val source: Source
)
