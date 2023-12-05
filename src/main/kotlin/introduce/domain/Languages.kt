package introduce.domain

class Languages {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    operator fun get(key: String): Int {
        return languages.getOrDefault(key, 0)
    }
}
