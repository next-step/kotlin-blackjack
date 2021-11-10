package study

class Language(val name: String, val score: Int)
class Languages(languages: List<Language>) : List<Language> by languages {
    companion object {
        fun empty() = Languages(emptyList())
    }
}

class LanguagesDsl {

    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(score: Int) {
        languages.add(Language(this, score))
    }

    fun toLanguages() = Languages(languages)
}
