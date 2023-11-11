package study

class Languages {

    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int): Languages {
        languages.add(Language(this, level))
        return this@Languages
    }

    fun introduce(): String {
        return "My languages level are ${languages.joinToString { it.introduce() }}"
    }
}

data class Language(val name: String, val level: Int) {
    fun introduce(): String {
        return "$name's level $level"
    }
}
