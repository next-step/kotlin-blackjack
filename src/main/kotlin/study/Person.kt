package study

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList(),
)
