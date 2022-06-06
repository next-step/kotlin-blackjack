package study

import study.language.Languages
import study.skill.Skill

data class Person(
    val name: String,
    val company: String?,
    val skill: Skill?,
    val languages: Languages?
)
