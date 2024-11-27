package person.domain.person.language

class LanguagesBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    fun languages(block: LanguagesBuilder.() -> Unit): Languages {
        return LanguagesBuilder().also(block).build()
    }

    infix fun String.level(value: Int) {
        languages.add(Language(name = this, level = value))
    }

    private fun build(): Languages {
        return Languages(languages.toList())
    }
}
