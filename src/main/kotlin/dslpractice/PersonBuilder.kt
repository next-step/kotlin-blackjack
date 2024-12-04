package dslpractice

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(value: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(value).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
