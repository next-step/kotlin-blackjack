package step1.language

data class Languages(
    val values: List<Language> = emptyList()
)

data class Language(
    val value: String,
    val level: Int
)
