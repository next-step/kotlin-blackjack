package study

class LanguagesBuilder {
    private val languages = mutableMapOf<Language, Level>()

    fun build(): Languages {
        return Languages(languages.toMap())
    }

    infix fun String.level(level: Int) = languages.put(Language(this), Level(level))
}

data class Languages(val languages: Map<Language, Level>) {
    operator fun get(language: String) = languages[Language(language)]?.value
}

@JvmInline
value class Language(val value: String)

@JvmInline
value class Level(val value: Int)
