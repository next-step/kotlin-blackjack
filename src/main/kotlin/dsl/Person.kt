package dsl

data class Person(val name: String, val company: String, val skills: List<Skill>)

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: List<Skill> = ArrayList()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(skills: List<Skill>) {
        this.skills = skills
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

fun introduce(block: (PersonBuilder) -> Unit): Person {
    val personBuilder = PersonBuilder()
    block(personBuilder)
    return personBuilder.build()
}
