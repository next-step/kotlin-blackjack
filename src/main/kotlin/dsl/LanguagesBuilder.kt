package dsl

class LanguagesBuilder {
    private var languages = Languages()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return languages
    }
}
