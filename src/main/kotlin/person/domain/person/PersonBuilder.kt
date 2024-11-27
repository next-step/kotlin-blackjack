package person.domain.person

import person.domain.person.language.Languages
import person.domain.person.language.LanguagesBuilder
import person.domain.person.skill.Skills
import person.domain.person.skill.SkillsBuilder

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        name = value
    }

    fun company(value: String?) {
        company = value ?: ""
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().skills(block)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().languages(block)
    }

    fun build(): Person =
        Person(
            name = name,
            company = company,
            skills = skills,
            languages = languages,
        )
}
