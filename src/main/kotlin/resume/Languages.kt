package resume

class Languages(private val language: MutableList<Language> = mutableListOf()) : List<Language> by language {
    infix fun String.level(level: Int) = language.add(Language(this, level))
}
