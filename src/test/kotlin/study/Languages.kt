package study

class Languages(
    val values: Map<String, Int>
)

class LanguagesBuilder {
    private val values: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        values[this] = level
    }

    fun build(): Languages {
        return Languages(values)
    }
}
