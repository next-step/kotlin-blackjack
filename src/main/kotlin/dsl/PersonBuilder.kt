package dsl

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block)
            .build()
    }


    fun build(): Person {
        return Person(
            name = name,
            company = company,
            skills = skills
        )
    }
}


fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block)
        .build()
}
