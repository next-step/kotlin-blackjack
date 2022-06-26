package dsl

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Language
) {

    companion object {
        fun introduce(block: PersonBuilder.() -> Unit): Person {
            return PersonBuilder().apply(block).build()
        }
    }
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Language

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(function: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(function).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
