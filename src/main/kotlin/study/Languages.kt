package study

class Languages(languages: List<Language> = emptyList()) {

    private val languages = languages.toMutableList()

    infix fun String.level(level: Int) {
        languages.add(Language(name = this, level = level))
    }

    operator fun contains(language: Language): Boolean = languages.contains(language)

    companion object {
        fun empty() = Languages()
    }
}
