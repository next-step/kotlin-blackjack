package dsl

class LanguagesBuilder {
    private val languages: MutableMap<LanguageName, LanguageLevel> = mutableMapOf()

    infix fun String.to(other: Int) {
        languages[this] = other
    }

    fun build(): Languages = Languages(languages)
}
