package resume.domain

import resume.domain.language.Languages
import resume.domain.skill.Skills

data class Person(val name: String, val company: String? = null, val skills: Skills, val languages: Languages)
