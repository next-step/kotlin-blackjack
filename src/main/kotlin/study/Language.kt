package study

data class Language(val name: String, val level: Int)

class LanguageBuilder {
    private val language: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        language.add(Language(this, level))
    }

    fun build() = language.toList()
}
