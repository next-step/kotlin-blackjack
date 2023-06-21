package study.dsl

data class Language(
    val language: String,
    val level: Int
)

class LanguageBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> = languages
}
