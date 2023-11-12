package dsl

class PersonBuilder(
    private var name: String = "",
    private var company: String = "",
    private var skills: Skills = Skills(emptyList(), emptyList()),
    private var languages: Languages = Languages(emptyList())
) {
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = introduceSkill(block)
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = introduceLanguage(block)
    }

    fun build(): Person = Person(
        name = name,
        company = company,
        skills = skills,
        languages = languages,
    )
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
