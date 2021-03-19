package study

class Languages private constructor(private val languages: Map<String, Int>) : Map<String, Int> by languages {
    class Builder {
        private val languages: MutableMap<String, Int> = mutableMapOf()

        infix fun String.level(level: Int) {
            languages[this] = level
        }

        fun build(): Languages {
            return Languages(languages)
        }
    }
}
