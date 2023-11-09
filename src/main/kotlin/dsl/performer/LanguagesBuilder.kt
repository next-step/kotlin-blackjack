package dsl.performer

data class LanguagesBuilder(
    private var languages: MutableSet<Language> = mutableSetOf(),
) {
    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
