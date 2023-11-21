package study

class LanguagesBuilder {
    private val _languages = mutableListOf<Pair<String, Int>>()
    val languages: List<Pair<String, Int>>
        get() = _languages

    fun language(language: Pair<String, Int>) {
        _languages.add(language)
    }

    fun build(): Languages {
        return Languages(_languages)
    }
}
