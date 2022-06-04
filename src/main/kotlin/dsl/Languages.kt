package dsl

class Languages(
    languages: List<Language>
) {

    private val _languages = languages.toMutableList()
    private val languages
        get() = _languages.toList()

    fun add(language: Language) {
        _languages.add(language)
    }
}
