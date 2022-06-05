package study

import study.language.Languages
import study.language.LanguagesBuilder
import study.skill.Skills
import study.skill.SkillsBuilder

class PersonBuilder {

    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills(listOf(), listOf())
    private var languages: Languages = Languages(listOf())

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
