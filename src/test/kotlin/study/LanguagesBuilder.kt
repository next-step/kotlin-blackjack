package study

class LanguagesBuilder {
    val languagesList = mutableListOf<LanguageLevel>()

    infix fun String.level(level: Int) {
        languagesList.add(LanguageLevel(this, level))
    }
}