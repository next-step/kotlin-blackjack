package study

class LanguagesBuilder {
    private val languages = mutableMapOf<Language, Level>()

    fun build(): Languages {
        return Languages(languages.toMap())
    }

    infix fun String.level(level: Int) = languages.put(Language(this), Level(level))
}

class Languages(private val languages: Map<Language, Level> = emptyMap()) {
    operator fun get(language: String) = languages[Language(language)]?.value
}

@JvmInline
value class Language(val value: String)

@JvmInline
value class Level(val value: Int)
