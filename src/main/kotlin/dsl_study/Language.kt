package dsl_study

data class Language(
    val name: String,
    val level: Int,
)

class LanguagesBuilder(
    private val languages: MutableList<Language> = mutableListOf(),
) {
    infix fun String.level(level: Int) {
        val language = Language(this, level)
        languages.add(language)
    }

    fun build(): List<Language> = languages.toList()
}
