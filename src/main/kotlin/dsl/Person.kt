package dsl

data class Person(
    var name: String = "",
    var company: String? = null,
    val skills: Skills = Skills(),
    val languages: Languages = Languages(),
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills = Skills()
    private val languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: Skills.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        languages.apply(block)
    }

    fun build(): Person = Person(name, company, skills, languages)
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
