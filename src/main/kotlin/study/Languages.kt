package study

class Languages(private val languages: MutableList<Language> = mutableListOf()) : List<Language> by languages {
    infix fun String.level(level: Int) {
        when (this) {
            "Korean" -> languages.add(Korean(level))
            "English" -> languages.add(English(level))
        }
    }
}
