fun introduce(block: PersonBuilder.() -> Unit): Person {
//    val personBuilder = PersonBuilder()
//    personBuilder.block()
//    return personBuilder.build()
    // 위의 3줄을 아래 1줄로 바꿀 수 있다.
    return PersonBuilder().apply(block).build()
}
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skill: Skill = Skill()
    private var languages: Languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: Skill.() -> Unit): Skill {
        return skill.apply(block)
    }

    fun languages(block: Languages.() -> Unit): Languages {
        return languages.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skill, languages)
    }
}
