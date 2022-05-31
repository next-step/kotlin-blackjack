package study

class Languages {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(lev: Int) {
        languages[this] = lev
    }

    override fun hashCode(): Int {
        return languages.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Languages) return false
        return languages == other.languages
    }
}
