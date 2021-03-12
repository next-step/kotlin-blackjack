package study

class Languages {

    private val elements: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) = elements.add(Language(this, level))

    fun toList(): List<Language> = elements
}
