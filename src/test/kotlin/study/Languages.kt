package study

data class Languages(val languages: Map<String, Int>)

class LanguagesBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Languages {
        return Languages(languages.toMap())
    }
}
