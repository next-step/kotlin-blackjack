package dsl

class Language(val language: String, val level: Int)

class LanguageBuilder {
    val languages = arrayListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }
}
