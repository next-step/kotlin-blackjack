package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder()
        .apply(block)
        .build()
}

data class PersonBuilder(
    private var name: Name = Name(),
    private var company: Company = Company(),
    private var skills: Skills = Skills(),
    private var languages: Languages = Languages(),
) {

    fun name(value: String) {
        name = Name(value)
    }

    fun company(value: String) {
        company = Company(value)
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder()
            .apply(block)
            .build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder()
            .apply(block)
            .build()
    }

    fun build() = Person(name, company, skills, languages)
}

data class Person(
    val name: Name,
    val company: Company,
    val skills: Skills,
    val languages: Languages
)

@JvmInline
value class Name(val value: String = "")

@JvmInline
value class Company(val value: String = "")
