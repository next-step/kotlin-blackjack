package dsl.language

class LanguagesBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(other: Int) {
        languages.add(Language(country = this, other))
    }

    fun build(): Languages = Languages(languages)
}
