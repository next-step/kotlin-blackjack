package dsl

data class Language(
    val language: String,
    val level: Int
)

@JvmInline
value class Languages(private val languages: List<Language> = emptyList())

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
