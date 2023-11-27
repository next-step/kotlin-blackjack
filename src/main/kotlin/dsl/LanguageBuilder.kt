package dsl

class LanguageBuilder {
    private val list = mutableListOf<Language>()
    infix fun String.level(level: Int) = Language(this, level).let(list::add)
    fun build() = Languages(list)
}
