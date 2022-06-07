package study

class LanguagesBuilder {
    private var languages: List<Language> = listOf()

    infix fun String.level(value: Int) {
        languages = languages.plus(Language(this, value))
    }

    fun build(): Languages {
        return Languages(this.languages)
    }
}
