package dsl.languages

class Languages(val languages: MutableList<Language> = mutableListOf()) : List<Language> by languages {
    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }
}
