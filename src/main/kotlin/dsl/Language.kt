package dsl

class Languages(languages: List<Language> = emptyList()) {
    private val _languages = languages.toMutableList()
    val languages: List<Language> get() = _languages.toList()

    fun add(language: Language) {
        _languages.add(language)
    }

}

data class Language(
    val name: String,
    val level: Int
)

