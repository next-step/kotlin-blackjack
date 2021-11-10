package resume

class Languages(private val languages: MutableList<Language> = mutableListOf()) : List<Language> by languages {

    infix fun String.level(int: Int) {
        languages.add(Language(this, int))
    }
}
