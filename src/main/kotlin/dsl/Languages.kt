package dsl

class Languages {
    private val _languages = mutableListOf<Language>()
    val languages get() = _languages.toList()

    infix fun String.level(level: Int) {
        val language = Language(this, level)
        _languages.add(language)
    }

    override fun toString(): String {
        return languages.joinToString("\n") {
            "${it.language} level ${it.level}"
        }
    }
}
