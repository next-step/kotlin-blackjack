package dsl

class Human {
    var name = ""
        private set

    var company = ""
        private set

    lateinit var language: Language

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

    fun languages(initializer: Language.() -> Unit) {
        this.language = Language().apply(initializer)
    }
}

fun introduce(initializer: Human.() -> Unit): Human {
    return Human().apply(initializer)
}
