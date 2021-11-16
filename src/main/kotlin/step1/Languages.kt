package step1

class Languages(private val languages: MutableMap<String, Int> = mutableMapOf()) : Map<String, Int> by languages {
    infix fun String.level(value: Int) {
        languages[this] = value
    }
}
