package study

@JvmInline
value class Languages(val values: List<Language>) {
    fun add(value: Language): Languages {
        return Languages(values.plus(value))
    }
}

data class Language(val name: String, val level: Int)
