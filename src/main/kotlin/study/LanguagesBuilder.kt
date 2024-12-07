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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Languages

        return languages == other.languages
    }

    override fun hashCode(): Int {
        return languages.hashCode()
    }
}

@JvmInline
value class Language(val value: String)

@JvmInline
value class Level(val value: Int)
