package studydsl

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills = Skills()
    private var languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build() = Person(name, company, skills, languages)
}
