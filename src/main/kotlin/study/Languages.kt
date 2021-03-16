package study

class Languages {

    private var _values: MutableMap<String, Int> = mutableMapOf()

    val values: Map<String, Int>
        get() = _values

    infix fun String.level(value: Int) {
        _values[this] = value
    }
}
