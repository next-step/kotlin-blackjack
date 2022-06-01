package study

@JvmInline
value class Languages(private val languages: List<Language> = emptyList())

data class Language(val type: String, val level: Int)

class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages = Languages(languages)
}
