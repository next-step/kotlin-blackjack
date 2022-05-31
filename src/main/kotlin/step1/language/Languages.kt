package step1.language

class Languages(languages: List<Language> = listOf()) {
    private var _languages: MutableList<Language> = languages.toMutableList()
    val list: List<Language>
        get() = _languages.toList()

    fun add(language: Language) {
        _languages.add(language)
    }
}
