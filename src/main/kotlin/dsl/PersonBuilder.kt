package dsl

import dsl.languages.Languages
import dsl.skills.Skills

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: Skills = Skills()
    private var language: Languages = Languages()

    fun build(): Person = Person(name, company, skills, language)

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(init: Skills.() -> Unit) {
        this.skills = Skills().apply(init)
    }

    fun languages(init: Languages.() -> Unit) {
        this.language = Languages().apply(init)
    }
}
