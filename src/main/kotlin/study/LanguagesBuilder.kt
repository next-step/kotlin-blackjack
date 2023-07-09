package study

class LanguagesBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(other: Int) {
        languages.add(Language(this, other))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
