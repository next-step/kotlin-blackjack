package dsl

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) = languages.add(Language(this, value))

    fun build(): Languages {
        return Languages(languages)
    }
}

data class Language(val type: String, val level: Int)
data class Languages(val list: List<Language>)
