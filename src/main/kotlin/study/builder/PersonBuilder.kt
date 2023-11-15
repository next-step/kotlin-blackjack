package study.builder

import study.domain.Language
import study.domain.Person
import study.domain.Skill

class PersonBuilder {
    private var name: String = "홍길동"
    private var company: String = "미정"
    private var skills: List<Skill> = emptyList()
    private var languages: List<Language> = emptyList()

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
