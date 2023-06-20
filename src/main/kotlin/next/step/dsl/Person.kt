package next.step.dsl

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

data class Person(val name: String, val company: String? = null, val languages: Languages, val skills: Skills)

class PersonBuilder {
    lateinit var name: String
    var company: String? = null
    var languages: Languages = Languages()
    var skills: Skills = Skills()
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, languages, skills)
    }
}