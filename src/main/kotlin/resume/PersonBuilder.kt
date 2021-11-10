package resume

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(initialize: Skills.() -> Unit) {
        skills = Skills().apply(initialize)
    }

    fun languages(initialize: Languages.() -> Unit) {
        languages = Languages().apply(initialize)
    }

    fun build(): Person = Person(name, company, skills, languages)
}
