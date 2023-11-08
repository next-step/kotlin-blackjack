package study

data class Languages(val list: List<Language>)

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages.toList())
    }
}
