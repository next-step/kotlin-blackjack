package study

class PersonBuilder(private var name: String) {
    private var company: String = ""
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()
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

    fun build() = Person(name, company, skills, languages)
}
