package dsl

class LanguagesBuilder(private var languages: Languages = Languages()) {
    infix fun String.level(value: Int) {
        val newLanguages = languages.add(Language(this, value))
        languages = newLanguages
    }

    fun build(): Languages {
        return languages
    }
}

data class Language(val type: String, val level: Int)

class Languages(val languages: List<Language> = emptyList()) {
    fun add(language: Language): Languages {
        return Languages(languages + listOf(language))
    }
}
