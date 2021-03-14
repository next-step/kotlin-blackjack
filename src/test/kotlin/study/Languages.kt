package study

class Languages {
    val values: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        values[this] = level
    }
}
