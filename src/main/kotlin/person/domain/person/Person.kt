package person.domain.person

import person.domain.person.language.Languages
import person.domain.person.skill.Skills

class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages,
)
