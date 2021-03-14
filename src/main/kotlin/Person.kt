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
        skills = Skills().apply(initializer)
    }

    fun languages(initializer: Languages.() -> Unit) {
        languages = Languages().apply(initializer)
    }
}
