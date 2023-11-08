package study

class Languages {

    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int): Languages {
        languages.add(Language(this, level))
        return this@Languages
    }

    override fun toString(): String {
        return "My languages level are $languages"
    }
}

data class Language(val name: String, val level: Int) {
    override fun toString(): String {
        return "$name's level $level"
    }
}
