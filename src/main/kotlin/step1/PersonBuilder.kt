package step1

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skill: Skill? = null
    private var language: Language? = null

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
        language = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skill, language)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
