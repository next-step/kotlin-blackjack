package dsl

data class Languages(private val _languages: MutableMap<Language, Level> = mutableMapOf()) {
    val languages: Map<Language, Level>
        get() = _languages.toMap()

    infix fun String.level(level: Int) = Level.of(level).also { _languages[Language.values(this)] = it }
}
