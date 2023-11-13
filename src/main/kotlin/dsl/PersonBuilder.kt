package dsl

class PersonBuilder {
    private var name: String = ""
    private var company: String? = null
    private var skills: List<Skill> = emptyList()
    private var languages: List<Language> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build() = Person(
        name = name,
        company = company,
        skills = skills,
        languages = languages,
    )
}
