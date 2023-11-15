package dsl

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills = Skills(listOf(), listOf()),
    val languages: Languages = Languages(listOf())
)
