package step1.person

import step1.language.Languages
import step1.language.LanguagesBuilder
import step1.skill.Skills
import step1.skill.SkillsBuilder

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

    fun build() = Person(name, company, skills, languages)
}
