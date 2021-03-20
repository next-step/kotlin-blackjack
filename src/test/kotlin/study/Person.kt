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

    fun skills(initialize: Skills.() -> Unit) {
        this.skills = Skills().apply(initialize)
    }

    fun languages(initialize: Languages.() -> Unit) {
        this.languages = Languages().apply(initialize)
    }

    fun build() = Person(name,skills, languages, company)
}
