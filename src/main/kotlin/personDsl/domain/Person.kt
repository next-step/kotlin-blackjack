package personDsl.domain

data class Person(
    val name: String,
    val company: String?,
    val softSkills: List<String>,
    val hardSkills: List<String>,
    val languages: List<LanguageLevel>,
)
