package study

data class Languages(val languages: List<Language>)

data class Language(val type: String, val level: Int)

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}