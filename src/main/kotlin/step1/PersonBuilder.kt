package step1

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skill: Skill? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skill = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit){
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skill, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
