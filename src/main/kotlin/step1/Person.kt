package step1

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)

class PersonBuilder(val name: String) {
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit){
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(name: String, block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder(name).apply(block).build()
}
