package study

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills(emptyList())
    private var language: List<Language> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        language = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, language)
    }
}
