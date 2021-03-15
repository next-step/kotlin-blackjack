class Languages {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun toList(): List<Language> = languages.toList()
}

data class Language(val name: String, val languageLevel: Int)
