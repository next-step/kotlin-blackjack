package study

class LanguageBuilder {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> {
        return languages.toList()
    }
}
