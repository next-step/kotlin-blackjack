package model

data class Language(
    val language: Type,
    val level: Int,
) {
    enum class Type(private val language: String) {
        KOREAN("Korean"),
        ENGLISH("English"),
        ;

        companion object {
            private val languageTypes = entries

            fun findByName(name: String): Type =
                languageTypes.find { it.language == name }
                    ?: throw IllegalArgumentException("Korean, English로 적어야 합니다.")
        }
    }
}
