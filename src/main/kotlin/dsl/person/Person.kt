package dsl.person

import dsl.language.Language
import dsl.skill.Skill

class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: List<Language>
)
