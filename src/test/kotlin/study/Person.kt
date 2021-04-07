package study

data class Person(
    val name: String = "",
    val company: String = "",
    val skills: Skills = Skills(),
    val languages: Languages = Languages()
)

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills()
    private var languages = Languages()

    fun name(lambda: () -> String) = apply { this.name = lambda() }
    fun company(lambda: () -> String) = apply { this.company = lambda() }
    fun skills(initializer: Skills.() -> Unit) = apply { skills = Skills().apply(initializer) }
    fun languages(initializer: Languages.() -> Unit) {
        languages = Languages().apply(initializer)
    }

    fun build() = Person(name, company, skills, languages)
}

fun introduce(initializer: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initializer).build()
}
