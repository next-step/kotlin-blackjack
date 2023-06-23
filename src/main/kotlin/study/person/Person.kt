package study.person

import study.languages.Languages
import study.skills.Skills

data class Person(
    val name: String,
    val company: String? = null,
    val skills: Skills,
    val languages: Languages
)
