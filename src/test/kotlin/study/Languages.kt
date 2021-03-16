package study

class Languages {
    private val _values: MutableMap<String, Int> = mutableMapOf()
    val values: Map<String, Int>
        get() = _values.toMutableMap()

    infix fun String.level(level: Int) {
        _values[this] = level
    }
}
