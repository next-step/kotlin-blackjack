package dsl

data class Languages(
    val value: List<Language>,
) {
    companion object {
        fun empty(): Languages = Languages(emptyList())
    }
}

class LanguageBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages = languages.toList().let(::Languages)
}

data class Language(
    private val name: String,
    private val level: Int
)
