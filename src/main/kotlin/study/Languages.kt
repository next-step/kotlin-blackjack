package study

class Languages(private val languages: MutableMap<String, Int> = mutableMapOf()) : Map<String, Int> by languages {
    infix fun String.level(level: Int) {
        languages[this] = level
    }
}
