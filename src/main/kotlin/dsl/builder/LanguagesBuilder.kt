package dsl.builder

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Map<String, Int> {
        return languages
    }
}
