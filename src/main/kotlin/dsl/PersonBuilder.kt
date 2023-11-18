package dsl

class PersonBuilder {

    private var name: String = ""
    private var company: String = ""
    private var skills: List<Skill> = emptyList()
    private var languages: List<Language> = emptyList()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person = Person(
        name = name,
        company = company,
        skills = skills,
        languages = languages,
    )
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
