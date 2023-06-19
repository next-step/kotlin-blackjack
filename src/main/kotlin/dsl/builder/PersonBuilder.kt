package dsl.builder

import dsl.domain.Languages
import dsl.domain.Person
import dsl.domain.Skills

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills
    private lateinit var languages: Languages

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
