package dsl

@JvmInline
value class Languages(private val languages: List<Language>) : List<Language> by languages

data class Language(val name: String, val level: Int)

class LanguageBuilder: DslBuilder<Languages>() {

    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(value: Int) {
        languages.add(
            element = Language(name = this, level = value),
        )
    }

    override fun build(): Languages = Languages(languages = languages.toList())
}
