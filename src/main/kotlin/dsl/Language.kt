package dsl

enum class Language(private val language: String) {
    KOREAN("Korean"),
    ENGLISH("English");

    companion object {
        fun values(language: String): Language = values().find {
            it.language == language
        } ?: throw IllegalArgumentException()
    }
}