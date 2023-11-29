package dsl

class Languages(val languages: Map<String, Int>)

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
