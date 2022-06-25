package dsl

data class Language(
    val languages: Map<String, Int>
)

class LanguageBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Language {
        return Language(languages)
    }
}
