import model.Language
import model.Languages

@PersonDsl
class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(num: Int) {
        languages.add(Language(Language.Type.findByName(this), num))
    }

    fun build(): Languages = Languages(languages)
}
