package dsl

class Language {
    private val values: MutableMap<String, Int> = mutableMapOf()

    fun values() = this.values.toMap()

    infix fun String.level(value: Int) {
        values[this] = value
    }
}
