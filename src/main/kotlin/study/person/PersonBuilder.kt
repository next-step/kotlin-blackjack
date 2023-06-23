package study.person

import study.languages.Languages
import study.languages.LanguagesBuilder
import study.skills.Skills
import study.skills.SkillsBuilder

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills(listOf())
    private var languages: Languages = Languages(listOf())

    fun name(name: String) {
        this.name = name
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
