package study

data class Languages(val languages: Map<Language, Level>)

class LanguagesBuilder {

    private val languages = mutableMapOf<Language, Level>()

    infix fun Language.level(level: Level) {
        languages[this] = level
    }

    fun build(): Languages {
        return Languages(languages = languages)
    }
}

private typealias Language = String
private typealias Level = Int
