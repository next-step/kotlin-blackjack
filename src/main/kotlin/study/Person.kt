package study

class Person(val name: String, val company: String?, val skills: List<Skill>, val languages: List<Language>)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = emptyList()
    private var language: List<Language> = emptyList()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.language = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, language)
    }
}
