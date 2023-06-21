class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(other: Int) = languages.add(Language(country = this, level = other))

    fun build(): Languages {
        return Languages(list = languages.toList())
    }
}
