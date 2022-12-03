package dsl

class LanguagesBuilder() {
    private var languages = Languages()

    infix fun String.level(value: Int) {
        val newLanguages = languages.add(Language(this, value))
        languages = newLanguages
    }

    fun build(): Languages {
        return languages
    }
}

data class Language(private val type: String, private val level: Int)

class Languages(languages: List<Language> = emptyList()) {
    private val _languages: MutableList<Language> = languages.toMutableList()
    val languages: List<Language>
        get() = _languages.toList()

    fun add(language: Language): Languages {
        _languages.add(language)
        return Languages(languages.toList())
    }
}
