package dsl

data class Languages(private val levels: Map<String, Int>) : Map<String, Int> by levels

class LanguagesBuilder {
    private var levels: MutableMap<String, Int>? = null

    infix fun String.level(level: Int) {
        if (levels == null) levels = mutableMapOf()
        levels?.put(this, level)
    }

    fun build(): Languages {
        return Languages(levels ?: emptyMap())
    }
}
