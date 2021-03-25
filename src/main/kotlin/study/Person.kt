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

    fun skills(initializer: Skills.() -> Unit): Skills {
        return Skills().apply(initializer).also { this.skills = it }
    }

    fun languages(initializer: Languages.() -> Unit): Languages {
        return Languages().apply(initializer).also { this.languages = it }
    }
}
