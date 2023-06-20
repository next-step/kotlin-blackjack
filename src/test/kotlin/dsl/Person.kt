package dsl

class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Languages,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var languages: Languages = Languages.empty()
    private var skills: Skills = Skills.empty()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()
