package step1

import step1.language.Languages
import step1.skill.Skills

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages
)
