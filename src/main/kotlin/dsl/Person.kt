package dsl

class PersonBuilder(
    private var name: String
) {
    private var company: String? = null
    private var skills: Skills = Skills.empty()
    private var languages: Languages = Languages.empty()

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
    val skills: Skills,
    val languages: Languages,
)
