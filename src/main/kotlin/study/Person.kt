package study

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList(),
)

@DslScope
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: MutableList<Skill> = mutableListOf()
    private var languages: MutableList<Language> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills.addAll(SkillsBuilder().apply(block).build())
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages.addAll(LanguagesBuilder().apply(block).build())
    }

    fun build(): Person = Person(name, company, skills, languages)
}
