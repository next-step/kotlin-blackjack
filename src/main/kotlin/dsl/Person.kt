package dsl

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: Skills.() -> Unit) {
        skills = Skills().apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        languages = Languages().apply(block)
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
