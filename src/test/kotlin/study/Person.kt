package study

data class Person(
    val name: String,
    val skills: Skills?,
    val languages: Languages?,
    val company: String?
)

class PersonBuilder {
    private var name: String = ""
    private var skills: Skills? = null
    private var languages: Languages? = null
    private var company: String? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(initialize: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(initialize).build()
    }

    fun languages(initialize: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(initialize).build()
    }

    fun build() = Person(name,skills, languages, company)
}
