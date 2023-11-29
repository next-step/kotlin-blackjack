package dsl

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private val softSkills: MutableList<String> = mutableListOf()
    private val hardSkills: MutableList<String> = mutableListOf()
    private val languages: MutableMap<String, Int> = mutableMapOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun skills(block: PersonBuilder.() -> Unit) {
        apply(block).build()
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, softSkills, hardSkills, languages)
    }
}
