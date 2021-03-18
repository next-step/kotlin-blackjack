package study

class Languages {

    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(name = this, level = level))
    }

    fun toList(): List<Language> = languages
}