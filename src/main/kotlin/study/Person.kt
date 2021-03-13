package study

class Person {
    lateinit var name: String
    lateinit var company: String
    val skills = Skills()
    val languages = Languages()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(initializer: Skills.() -> Unit) {
        this.skills.apply(initializer)
    }

    fun languages(initializer: Languages.() -> Unit) {
        this.languages.apply(initializer)
    }
}
