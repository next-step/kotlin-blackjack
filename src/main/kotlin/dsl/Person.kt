package dsl

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String?) {
        company = value
    }

    fun skills(
        block: SkillsBuilder.() -> Unit
    ) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(
        block: LanguageBuilder.() -> Unit
    ) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)
