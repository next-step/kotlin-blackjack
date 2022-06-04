package dsl

data class Person(
    val name: String,
    val company: String,
    val languages: Languages,
    val skills: Skills,
)

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var languages: Languages = Languages()
    private var skills: Skills = Skills()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder()
            .apply(block)
            .build()
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block)
            .build()
    }

    fun build(): Person = Person(
        name,
        company,
        languages,
        skills,
    )
}
