package person.domain

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val hardSkills: MutableList<String> = mutableListOf()
    private val softSkills: MutableList<String> = mutableListOf()
    private val languages: MutableMap<String, Int> = mutableMapOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String?) {
        company = value
    }

    fun skills(block: PersonBuilder.() -> Unit) {
        block()
    }

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        block()
    }

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Person = Person(name, company, hardSkills.toList(), softSkills.toList(), languages.toMap())
}
