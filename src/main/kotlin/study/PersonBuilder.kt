package study

import study.skill.Skill

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: MutableList<Skill> = mutableListOf()
    private var languages: MutableMap<String, Int> = mutableMapOf()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
