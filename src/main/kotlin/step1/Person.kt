package step1

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: List<Language>)

fun introduce(builder: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(builder).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: List<Skill>
    private lateinit var languages: List<Language>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(builder: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(builder).build()
    }

    fun languages(builder: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(builder).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
