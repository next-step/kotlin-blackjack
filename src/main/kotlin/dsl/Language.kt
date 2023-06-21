package dsl

data class Language(val value: Pair<String, Int>)
data class LanguageBuilder(var languages: MutableList<Language> = mutableListOf()) {
    infix fun String.level(level: Int) = languages.add(Language(Pair(this, level)))
    fun build(): LanguageBuilder = LanguageBuilder(languages)
}
