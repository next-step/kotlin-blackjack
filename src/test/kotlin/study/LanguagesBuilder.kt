package study

class LanguagesBuilder {
    private val list = mutableListOf<Language>()
    infix fun String.level(level: Int) = list.add(Language(this, level))
    fun build(): List<Language> = list
}
