package study

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList()
)

class PersonBuilder(private val name: String) {
    private var company: String? = null
    private var skills = emptyList<Skill>()
    private var languages = emptyList<Language>()

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
