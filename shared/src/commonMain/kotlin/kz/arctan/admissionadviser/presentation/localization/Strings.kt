package kz.arctan.admissionadviser.presentation.localization

object Strings {
    const val TYPE_YOUR_MESSAGE_HERE_KEY = "type_your_message_here"
    const val SEND_MESSAGE = "send_message"

    enum class Language { KAZAKH, RUSSIAN, ENGLISH }

    private val map = mapOf(
        TYPE_YOUR_MESSAGE_HERE_KEY to mapOf(
            Language.KAZAKH to "Осы жерде жазыныз",
            Language.ENGLISH to "Type your message here",
            Language.RUSSIAN to "Тут пишите сообщение"
        ),
        SEND_MESSAGE to mapOf(
            Language.KAZAKH to "Осы жерде жазыныз",
            Language.ENGLISH to "Send message",
            Language.RUSSIAN to "ТОтправить сообщение"
        )
    )

    operator fun get(key: String, language: Language): String? = map[key]?.get(language)
}