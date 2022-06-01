package dsl

class Person(val name: String, val company: String, val skills: List<Skill>)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

@DslMarker
annotation class PersonMaker

@PersonMaker
class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: List<Skill> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).skills.toList()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}
