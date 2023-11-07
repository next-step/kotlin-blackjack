package study

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()
    infix fun String.level(level: Int) {
        languages.add(Language.from(this, level))
    }

    fun build() = Languages(languages)
}