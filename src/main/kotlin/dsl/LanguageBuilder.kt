package dsl

class LanguageBuilder {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        Language(name = this, level = level).also { languages.add(it) }
    }

    fun build(): List<Language> = languages
}
