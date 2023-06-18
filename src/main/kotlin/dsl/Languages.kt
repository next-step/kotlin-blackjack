package dsl

class Languages(
    val values: List<Language> = listOf(),
)

class LanguagesBuilder {
    private val values: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        values.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(values.toList())
    }
}
