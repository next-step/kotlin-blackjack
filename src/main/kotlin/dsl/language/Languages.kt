package dsl.language

@JvmInline
value class Languages(
    val values: List<Language> = listOf(),
)

class LanguagesBuilder {
    private val values: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        values.add(Language(this, level))
    }

    fun build(): Languages = Languages(values.toList())
}
