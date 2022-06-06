package kotlindsl

class LanguageBuilder {
    private val _levelMap = mutableMapOf<String, Int>()

    infix fun String.level(value: Int) {
        _levelMap[this] = value
    }

    private val levelMap: Map<String, Int>
        get() = _levelMap.toMap()

    fun toBuilder(): Languages {
        return Languages(levelMap)
    }
}
data class Languages(val levelMap: Map<String, Int>)
