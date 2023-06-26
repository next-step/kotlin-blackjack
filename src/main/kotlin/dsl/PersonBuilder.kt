package dsl

import dsl.language.Language
import dsl.language.LanguagesBuilder
import dsl.person.Person
import dsl.skill.Skill
import dsl.skill.SkillsBuilder

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill>? = null
    private var languages: List<Language>? = null

    fun name(value: String) {
        this.name = value
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
        return Person(this.name, this.company, this.skills ?: emptyList(), this.languages ?: emptyList())
    }
}
