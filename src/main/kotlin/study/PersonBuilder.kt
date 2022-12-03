package study

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skill: Skill
    private lateinit var language: List<Language>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skill = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        language = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skill, language)
    }
}
