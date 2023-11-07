package study

class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(step: Int) = languages.add(Language(this, step))

    fun build() = languages
}

data class Language(val name: String, val level: Int)
