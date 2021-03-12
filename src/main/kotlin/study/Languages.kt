package study

class Languages {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(other: Int) {
        languages.add(Language(this, other))
    }

    fun toList(): List<Language> {
        return languages
    }
}

data class Language(val language: String, val level: Int)
