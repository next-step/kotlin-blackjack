package dsl
class Language(val language: String, val level: Int)
@JvmInline
value class Languages(val values: List<Language>)
class LanguagesBuilder {
    private val languages = mutableListOf<Language>()
    infix fun String.level(level: Int){
        languages.add(Language(this, level))
    }
    fun build(): Languages {
        return Languages(languages)
    }
}
