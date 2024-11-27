package person

import person.language.Language
import person.language.LanguagesBuilder
import person.skill.Skill
import person.skill.SkillsBuilder

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = listOf()
    private var languages: List<Language> = listOf()

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
