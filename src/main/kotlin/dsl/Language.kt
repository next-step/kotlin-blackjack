package dsl

data class Language(val language: String, val level: Int)

data class Languages(val values: List<Language> = listOf()) {
    val size: Int get() = values.size

    operator fun get(index: Int): Language = values[index]
}

class LanguagesBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages = Languages(languages)
}
