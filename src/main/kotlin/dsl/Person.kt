package dsl

data class Person(
    val name: String,
    val company: String?,
    val skill: Skill,
    val languages: Languages
)
