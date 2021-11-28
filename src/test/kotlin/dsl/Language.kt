package dsl

data class LanguagesBuilder(
    private var values: MutableList<Language> = mutableListOf(),
) {
    infix fun String.level(value: Int) {
        values.add(Language(this, value))
    }

    fun build(): Languages {
        return Languages(values)
    }
}

data class Languages(private val values: List<Language> = emptyList()) : List<Language> by values
data class Language(val name: String, val level: Int)
