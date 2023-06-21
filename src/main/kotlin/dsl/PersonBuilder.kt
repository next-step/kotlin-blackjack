package dsl

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = SkillsBuilder().build()
    private var languages: Languages = LanguagesBuilder().build()
    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
