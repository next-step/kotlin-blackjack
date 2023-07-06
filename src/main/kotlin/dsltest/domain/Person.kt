package dsltest.domain

data class Person(
    val name: String,
    val company: String? = null,
    val skills: List<Skill>,
    val languages: List<Language>
)
