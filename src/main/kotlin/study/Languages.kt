package study

class Languages {
    private val _languages = mutableMapOf<String, Int>()

    val languages: Map<String, Int>
        get() = _languages.toMap()

    infix fun String.level(level: Int) {
        _languages[this] = level
    }
}
