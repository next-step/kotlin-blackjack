package study

class PersonBuilder {

    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills(emptyList())
    private var languages: Languages = Languages(emptyList())

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block)
            .build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block)
            .build()
    }

    fun build(): Person {
        return Person(
            name,
            company,
            skills,
            languages
        )
    }
}
