package dsl.domain

/**
 * Created by Jaesungchi on 2022.06.03..
 */
class Languages(languages: List<Language> = emptyList()) {
    private val _languages = languages.toMutableList()
    val languages: List<Language> get() = _languages.toList()

    fun add(language: Language) {
        _languages.add(language)
    }
}
