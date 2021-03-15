package study

class Person {

    var name = ""
        private set

    var company = ""
        private set

    lateinit var languages: Languages

    lateinit var skills: Skills

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

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}
