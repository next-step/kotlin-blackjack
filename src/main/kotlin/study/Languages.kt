package study

class Languages {

    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun getLanguages(): Map<String, Int> = languages.toMap()
}
