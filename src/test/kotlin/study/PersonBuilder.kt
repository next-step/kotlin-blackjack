package study

fun introduce(block: PersonBuilder.() -> Unit): Person { // PersonBuilder 내부의 함수를 인자로 받는다.
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: Skills = Skills(emptyList(), "")
    private var languages: Languages = Languages(emptyMap())

    fun name(value: String) {
        name = value
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
