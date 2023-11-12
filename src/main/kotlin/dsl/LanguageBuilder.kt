package dsl

class LanguageBuilder(
    private var languages: MutableList<Language> = mutableListOf()
) {
    infix fun String.level(level: Int) {
        languages.add(Language.from(this, level))
    }

    fun build(): Languages = Languages(languages)
}

fun introduceLanguage(block: LanguageBuilder.() -> Unit): Languages {
    return LanguageBuilder().apply(block).build()
}
