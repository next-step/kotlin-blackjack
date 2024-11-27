package dsl.person

import dsl.skill.Skills

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
)
