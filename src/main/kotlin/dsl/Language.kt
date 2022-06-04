package dsl

data class Language(
    val name: String,
    val level: Int,
)

class Languages(values: List<Language> = listOf()) {
    private val _values = values.toMutableList()
    val values: List<Language> get() = _values.toList()

    fun add(language: Language) {
        _values.add(language)
    }
}

class LanguagesBuilder {
    private val languages = Languages()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return languages
    }
}
