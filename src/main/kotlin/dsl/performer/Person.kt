package dsl.performer

data class Person(
    val name: String?,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,

    ) {
    override fun toString(): String {
        return """
        name=${this.name}\n
        company=${this.company}
        skills=[${this.skills}]
        languages=[${this.languages}]
        """.trimMargin()

    }
}

data class PersonBuilder(
    private var name: String? = null,
    private var company: String? = null,
    private var skills: Skills? = null,
    private var languages: Languages? = null,
) {
    fun name(value: String) {
        this.name = value
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

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
