package dsl

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var languages: Languages? = null
    private var skills: Skills? = null

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
        return Person(name, company, languages, skills)
    }
}
