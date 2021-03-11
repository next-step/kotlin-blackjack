package study

class Person {
    lateinit var name: String
    var company: String? = null
    lateinit var skills: Skills
    lateinit var languages: Languages

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(initializer: Skills.() -> Unit) {
        this.skills = Skills().apply(initializer)
    }

    fun languages(initializer: Languages.() -> Unit) {
        this.languages = Languages().apply(initializer)
    }
}
