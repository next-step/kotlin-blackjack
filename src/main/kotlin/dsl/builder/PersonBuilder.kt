package dsl.builder

import dsl.domain.Languages
import dsl.domain.Person
import dsl.domain.Skills

class PersonBuilder(block: PersonBuilder.() -> Unit) {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    init {
        apply(block)
    }

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
