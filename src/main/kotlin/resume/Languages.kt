package resume

data class Language(val name: String, val level: Int)
class Languages(private val language: MutableList<Language> = mutableListOf()) : List<Language> by language {
    infix fun String.level(level: Int) = language.add(Language(this, level))
}
