package learning

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills = mutableListOf<Skill>()
    private var languages = mutableListOf<Language>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        val skills = Skills(skills)
        val languages = Languages(languages)
        return Person(name, company, skills, languages)
    }
}
