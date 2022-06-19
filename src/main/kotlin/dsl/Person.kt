package dsl

data class Person(
    val name: String,
    val company: String,
    val skills: List<Skill>,
    val languages: List<Language>
)

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

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build() =
        Person(name, company, skills, languages)
}

fun instroduce(block: PersonBuilder.() -> Unit) =
    PersonBuilder().apply(block).build()
