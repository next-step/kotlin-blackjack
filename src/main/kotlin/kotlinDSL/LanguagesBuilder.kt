package kotlinDSL

class LanguagesBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(grade: Int) {
        Language(this, grade).also { languages.add(it) }
    }

    fun build(): List<Language> {
        return languages
    }
}
