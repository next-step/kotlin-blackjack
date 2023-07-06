package dsltest.domain

class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(step: Int) = languages.add(Language(this, step))
    fun build() = languages
}
