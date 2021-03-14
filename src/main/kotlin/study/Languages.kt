package study

class Languages {

    private var _values: MutableMap<String, Int> = mutableMapOf()

    val values: Map<String, Int>
        get() = _values.toMap()

    infix fun String.level(value: Int) {
        _values[this] = value
    }
}
