package dsl

class Languages {
    private val _languages: MutableList<Language> = mutableListOf()
    val languages
        get() = _languages.toList()

    infix fun String.level(level: Int) {
        _languages.add(Language(this, level))
    }
}

data class Language(
    val value: String,
    val level: Int
)
