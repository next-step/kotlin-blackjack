package learning

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): MutableList<Language> {
        return languages
    }
}
