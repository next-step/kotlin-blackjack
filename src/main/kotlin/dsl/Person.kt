package dsl

import dsl.language.Languages
import dsl.language.LanguagesBuilder
import dsl.skill.Skills
import dsl.skill.SkillsBuilder

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Languages,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}
