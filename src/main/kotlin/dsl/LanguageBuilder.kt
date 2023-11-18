package dsl

class LanguageBuilder {
    private val languages: MutableList<Language> = ArrayList()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }
}
