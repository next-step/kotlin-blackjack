package dsl

data class Person(val name: String, val company: String? = null, val skills: Skills? = null, val languages: List<Language> = listOf())

class PersonBuilder {

    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: List<Language> = listOf()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
