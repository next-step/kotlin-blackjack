package study

fun introduce(person: PersonBuilder.() -> Unit): Person { // PersonBuilder 내부의 함수를 인자로 받는다.
    return PersonBuilder().apply(person).build()
}

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages
)

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(skills: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder()
            .apply(skills)
            .build()
    }

    fun languages(languages: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder()
            .apply(languages)
            .build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
