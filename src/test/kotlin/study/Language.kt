package study

data class Language(val name: String, val level: Int)

class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
