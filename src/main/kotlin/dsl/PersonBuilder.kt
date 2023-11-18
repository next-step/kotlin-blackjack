package dsl

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: List<Skill> = ArrayList()
    private var languages: List<Language> = ArrayList()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(languages: List<Language>) {
        this.languages = languages
    }
    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    val personBuilder = PersonBuilder()
    block(personBuilder)
    return personBuilder.build()
}
