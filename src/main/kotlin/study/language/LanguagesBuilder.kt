package study.language

class LanguagesBuilder {

    private var language: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        language.add(Language(this, level))
    }

    fun build(): Languages = Languages(language)
}
