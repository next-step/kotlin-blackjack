package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String? = null,
    val skills: List<Skill>,
    val languages: List<Language>
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = listOf()
    private var languages: List<Language> = listOf()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills.toList(), languages.toList())
    }
}
