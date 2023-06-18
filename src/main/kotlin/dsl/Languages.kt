package dsl

@JvmInline
value class Languages(private val values: List<Language> = emptyList()) : List<Language> by values

@DeveloperMarker
class LanguagesBuilder : Builder<Languages> {
    private val values = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        values.add(Language(this, value))
    }

    override fun build(): Languages = Languages(values.toList())
}
