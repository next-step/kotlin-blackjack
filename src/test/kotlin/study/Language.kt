package study

class Language(val language: String, val level: Int = 0)

class LanguageBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(i: Int) {
        languages.add(Language(this, i))
    }

    fun build(): List<Language> {
        return languages.toList()
    }
}
