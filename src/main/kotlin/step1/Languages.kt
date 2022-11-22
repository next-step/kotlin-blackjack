package step1

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
