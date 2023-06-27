package study

class LanguageBuilder : Builder<List<Language>> {
    private var languages: List<Language> = emptyList()

    infix fun String.level(value: Int) {
        languages += Language(this, value)
    }

    override fun build(): List<Language> {
        return languages.toList()
    }
}