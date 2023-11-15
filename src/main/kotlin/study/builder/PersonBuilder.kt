package study.builder

import study.dto.Language
import study.dto.Person
import study.dto.Skill

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: List<Skill>
    private lateinit var languages: List<Language>

    fun name(value: String) {
        name = value
    }

    fun company(value: String?) {
        company = value ?: "미정"
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LangaugeBuilder.() -> Unit) {
        languages = LangaugeBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
