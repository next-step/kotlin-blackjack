package study

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: Skills = Skills(emptyList(), emptyList())
    private var languages: List<Language> = emptyList()

    fun name(name: String) {
        this.name = name
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

/**
 * Person에 있는 함수만 이용하겠다는 것을 말한다.
 */
fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
