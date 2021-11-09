package dsl.person

import dsl.languages.Languages
import dsl.skills.Skills

data class Person(val name: String?, val company: String?, val skills: Skills?, val language: Languages?)
