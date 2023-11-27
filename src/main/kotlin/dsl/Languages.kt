package dsl

class Languages {
    private val languageMap: HashMap<String, Int> = hashMapOf()
    infix fun String.level(level: Int): Unit = putLanguage(this, level)

    private fun putLanguage(language: String, level: Int) {
        languageMap[language] = level
    }

    fun getLanguageLevel(language: String): Int? {
        return languageMap[language]
    }
}
