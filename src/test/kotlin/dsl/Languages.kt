package dsl

class Language(val name: String, val level: Int)

class Languages(val values: List<Language>) {
    val size: Int
        get() = values.size

    companion object {
        fun empty(): Languages = Languages(emptyList())
    }
}

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
