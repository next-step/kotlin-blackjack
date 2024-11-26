package study.builder

import study.domain.Language
import study.domain.Person

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null // 회사가 없을 수도 있음
    private var skills: List<String> = emptyList()
    private var languages: List<Language> = emptyList()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(vararg languages: Language) {
        this.languages = languages.toList()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}