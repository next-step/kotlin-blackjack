package dsl

data class Languages(
    val languages: List<Language>
)

data class Language(
    val name: String,
    val level: Int
)
