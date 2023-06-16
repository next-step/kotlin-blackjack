package dsl

data class Person(val name: String, val company: String?, val languages: Languages?, val skills: Skills?)

@PersonDsl
class PersonBuilder {

    private lateinit var name: String
    private var company: String? = null
    private var languages: Languages? = null
    private var skills: Skills? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block = block).build()
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block = block).build()
    }

    fun build(): Person {
        return Person(
            name = name,
            company = company,
            languages = languages,
            skills = skills,
        )
    }
}
