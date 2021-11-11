package dsl

data class Languages(private val _languages: MutableMap<Language, Int> = mutableMapOf()) {
    val languages: Map<Language, Int>
        get() = _languages.toMap()

    infix fun String.level(level: Int) = level.also {
        _languages[Language.values(this)] = it
    }
}