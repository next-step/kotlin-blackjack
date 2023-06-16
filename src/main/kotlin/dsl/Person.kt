package dsl

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = introduceSkills(block)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = introduceLanguages(block)
    }

    fun build() = Person(name, company, skills, languages)
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
