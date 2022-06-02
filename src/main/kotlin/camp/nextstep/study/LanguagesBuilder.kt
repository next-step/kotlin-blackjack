package camp.nextstep.study

class LanguagesBuilder {
    private var languages = mutableMapOf<Language, Language.Level>()

    fun build(): Languages {
        return Languages(languages)
    }

    infix fun String.level(level: Int) {
        languages[Language(this)] = Language.Level(level)
    }
}
