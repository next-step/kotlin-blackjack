package studydsl

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(name = this, level = level))
    }

    fun build() = Languages(languages = languages)
}
