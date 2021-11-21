package dsl

enum class Language(private val language: String) {
    KOREAN("Korean"),
    ENGLISH("English");

    companion object {
        private const val NOT_FOUND_LANGUAGE_MESSAGE = "%s라는 언어를 찾지 못했습니다"
        fun values(language: String): Language = values()
            .find { it.language == language }
            ?: throw IllegalArgumentException(NOT_FOUND_LANGUAGE_MESSAGE.format(language))
    }
}
