package study

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder()
            .apply(block)
            .build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder()
            .apply(block)
            .build()
    }

    fun build(): Person {
        return Person(name = name, company = company, skills = skills, languages = languages)
    }
}
