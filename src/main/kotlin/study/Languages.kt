package study

class Languages {
    private val _languages: MutableList<Language> = mutableListOf()
    private val languages: List<Language>
        get() = _languages

    infix fun String.level(level: Int) {
        _languages.add(Language(this, level))
    }

    fun toList(): List<Language> {
        return languages
    }
}
