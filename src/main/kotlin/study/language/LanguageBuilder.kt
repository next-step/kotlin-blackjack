package study.language

class LanguageBuilder {
    private var language = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        language.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(language.toList())
    }
}
