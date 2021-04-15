package study

class Languages {
    private var _languages = mutableListOf<Language>()

    var languages = emptyList<Language>()
        get() = _languages.toList()

    fun add(language: Language) {
        _languages.add(language)
    }
}
