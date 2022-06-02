package kotlindsl

data class Person(
    val name: String,
    val company: String,
    val skills: List<Skill>,
    val languages: List<Language>
)

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private val skills = Skills()
    private val languages = Languages()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: Skills.() -> Unit) {
        this.skills.block()
    }

    fun languages(block: Languages.() -> Unit) {
        this.languages.block()
    }

    fun build() = Person(
        name = this.name,
        company = this.company,
        skills = this.skills.toList(),
        languages = this.languages.toList()
    )
}

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder().apply(block).build()
