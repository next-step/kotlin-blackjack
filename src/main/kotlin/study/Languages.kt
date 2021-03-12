package study

class Languages {
    val langugages: MutableList<Language> = mutableListOf()
    infix fun String.level(level: Int) = langugages.add(Language(this, level))
    fun toList(): List<Language> = langugages
}

data class Language(val name: String, val level: Int)