package study

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skill: Skill? = null
    private var language: Map<String, Int> = mutableMapOf()

    fun name(name: String) {
        this.name = name
    }

    fun company(value: String) {
        company = value
    }

    fun skill(block: SkillBuilder.() -> Unit) {
        this.skill =  SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.language = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skill, language)
    }
}
