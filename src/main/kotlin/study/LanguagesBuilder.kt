package study

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}

data class Languages(val value: List<Language>)
data class Language(val kindOfLanguage: String, val level: Int)
