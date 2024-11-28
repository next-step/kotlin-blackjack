package person

import person.language.Language
import person.skill.Skill

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: List<Language>,
)
