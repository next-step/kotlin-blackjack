package kotlindsl

class Languages {
    private val _languages: MutableList<Language> = mutableListOf()

    val languages: List<Language>
        get() = _languages.toList()

    infix fun String.level(level: Int) {
        _languages.add(Language(this, level))
    }
}
