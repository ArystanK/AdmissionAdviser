package kz.arctan.admissionadviser.presentation.localization

object Strings {
    const val TYPE_YOUR_MESSAGE_HERE_KEY = "type_your_message_here"
    const val SEND_MESSAGE = "send_message"
    const val ADMISSION_ADVISER = "admission_adviser"
    const val ENTER = "enter"
    const val START = "start"
    const val RUSSIAN = "russian"
    const val KAZAKH = "kazakh"
    const val ENGLISH = "english"
    const val WELCOME_TEXT = "welcome_text"
    const val WELCOME_TEXT_DESKTOP = "welcome_text_desktop"

    enum class Language { KAZAKH, RUSSIAN, ENGLISH }

    private val map = mapOf(
        TYPE_YOUR_MESSAGE_HERE_KEY to mapOf(
            Language.KAZAKH to "Осы жерде жазыныз",
            Language.ENGLISH to "Type your message here",
            Language.RUSSIAN to "Тут пишите сообщение"
        ),
        SEND_MESSAGE to mapOf(
            Language.KAZAKH to "Хабарлама жіберу",
            Language.ENGLISH to "Send message",
            Language.RUSSIAN to "Отправить сообщение"
        ),
        ADMISSION_ADVISER to mapOf(
            Language.ENGLISH to "Admission Adviser",
            Language.RUSSIAN to "Советник по поступлению",
            Language.KAZAKH to "Қабылдау жөніндегі кеңесші"
        ),
        ENTER to mapOf(
            Language.ENGLISH to "Log In",
            Language.RUSSIAN to "Войти",
            Language.KAZAKH to "Кіру"
        ),
        START to mapOf(
            Language.ENGLISH to "Start!",
            Language.RUSSIAN to "Начнем!",
            Language.KAZAKH to "Бастайық!"
        ),
        ENGLISH to mapOf(
            Language.ENGLISH to "Eng",
            Language.RUSSIAN to "Англ",
            Language.KAZAKH to "Ағыл"
        ),
        RUSSIAN to mapOf(
            Language.ENGLISH to "Rus",
            Language.RUSSIAN to "Рус",
            Language.KAZAKH to "Орыс"
        ),
        KAZAKH to mapOf(
            Language.ENGLISH to "Kaz",
            Language.RUSSIAN to "Каз",
            Language.KAZAKH to "Қаз"
        ),
        WELCOME_TEXT to mapOf(
            Language.RUSSIAN to "Новый ИИ, который ответит на ваши вопросы.",
            Language.ENGLISH to "New AI that will answer your questions.",
            Language.KAZAKH to "Сұрақтарыңызға жауап беретін жаңа ЖИ."
        ),
        WELCOME_TEXT_DESKTOP to mapOf(
            Language.RUSSIAN to "Получайте первым всю информацию о поступлении,грантах,скидках,конкурсах и обучении в университете!",
            Language.ENGLISH to "Be the first to receive all the information about admission, grants, discounts, competitions and studying at the university!",
            Language.KAZAKH to "Қабылдау, гранттар, жеңілдіктер, конкурстар және университетте оқу туралы барлық ақпаратты бірінші болып алыңыз!"
        )
    )

    operator fun get(key: String, language: Language): String = map[key]?.get(language) ?: ""
}