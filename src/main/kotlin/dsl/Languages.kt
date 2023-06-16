package dsl

data class Languages(
    val languages: List<Language>,
)

data class Language(
    val name: String,
    val level: Int,
)

class LanguagesBuilder {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build() = Languages(languages)
}

fun introduceLanguages(block: LanguagesBuilder.() -> Unit): Languages {
    return LanguagesBuilder().apply(block).build()
}
