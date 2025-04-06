import models.Language

class LanguageBuilder {

    private var _languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        _languages.add(
            Language(
                name = this,
                proficiency = value
            )
        )
    }

    fun build(): List<Language> = _languages
}