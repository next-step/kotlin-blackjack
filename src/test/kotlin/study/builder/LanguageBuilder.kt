package study.builder

data class Language(val name: String, val level: Int)

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> = languages.toList()
}
