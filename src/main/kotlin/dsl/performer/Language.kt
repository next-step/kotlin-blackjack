package dsl.performer

data class Language(
    val lang: String,
    val level: Int,

) {
    override fun toString(): String {
        return "Language(lang=$lang level=$level)"
    }
}

data class Languages(
    val languages: Set<Language>
)

class LanguagesBuilder {
    private val languages = mutableSetOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
