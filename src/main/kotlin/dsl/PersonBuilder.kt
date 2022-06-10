package dsl

import dsl.skill.Skills
import dsl.skill.SkillsBuilder

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills(listOf(), listOf())

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}
