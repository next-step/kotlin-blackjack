package study

class Person {
    lateinit var name: String
    lateinit var skills: Skills
    lateinit var languages: Languages
    var company: String? = null

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
}
