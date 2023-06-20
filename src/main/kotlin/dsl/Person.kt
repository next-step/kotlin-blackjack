package dsl

data class Person(val name: String, val company: String? = null, val skills: Skills? = null)

class PersonBuilder {

    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skill(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
