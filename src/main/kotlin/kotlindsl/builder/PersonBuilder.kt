package kotlindsl.builder

import kotlindsl.model.Language
import kotlindsl.model.Person
import kotlindsl.model.Skill

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill>? = null
    private var language: List<Language>? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.language = LanguageBuilder().apply(block).build()
    }

    fun build(): Person = Person(this.name, this.company, this.skills, this.language)
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
