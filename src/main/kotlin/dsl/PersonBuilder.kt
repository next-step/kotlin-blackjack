package dsl

import dsl.language.Languages
import dsl.language.LanguagesBuilder
import dsl.skill.Skills
import dsl.skill.SkillsBuilder

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills? = null
    private var languages: Languages? = null
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

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
