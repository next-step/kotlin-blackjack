package study

data class Language(val name: String, val level: Int)

class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): MutableList<Language> {
        val result = languages.map { it.copy() }
        languages.clear()
        return result.toMutableList()
    }
}
