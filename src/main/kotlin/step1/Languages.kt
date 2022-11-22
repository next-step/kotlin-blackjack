package step1

data class Language(
    val name: String,
    val level: Int,
)

data class Languages(
    val value: List<Language>
)

class LanguageBuilder(
    private val value: MutableList<Language> = mutableListOf()
) {
    infix fun String.level(level: Int) {
        value.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(value)
    }
}
