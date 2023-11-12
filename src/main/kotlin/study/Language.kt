package study

typealias Language = Pair<String, Int>

class LanguageBuilder {
    private var languages: MutableList<Language> = emptyList<Language>().toMutableList()

    infix fun String.level(level: Int): Language {
        val lang = Pair(this, level)
        languages.add(lang)
        return lang
    }

    fun build(): List<Language> {
        return languages
    }
}
