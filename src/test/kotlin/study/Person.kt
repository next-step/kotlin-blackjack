package study

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages,
)

inline fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder()
        .apply(block)
        .build()

class PersonBuilder {
    private lateinit var name: String
    private var company = ""
    private var skills = Skills()
    private var languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = (SkillBuilder().apply(block).build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = (LanguageBuilder().apply(block).build())
    }

    fun build() = Person(name, company, skills, languages)
}
