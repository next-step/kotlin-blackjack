package dsl.language

class LanguagesBuilder {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages.toList())
    }
}
