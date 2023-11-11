package study

class LanguagesBuilder {
    private val languagesList = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languagesList.add(Language(this, level))
    }

    fun build(): List<Language> = languagesList
}
