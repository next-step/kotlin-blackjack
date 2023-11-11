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

    fun skills(value: Skills) {
        skills = value
    }

    fun languages(value: Languages) {
        languages = value
    }

    fun builder(): Person = Person(
        name = name,
        company = company,
        skills = skills,
        languages = languages,
    )
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).builder()
}

infix fun String.of(int: Int): Pair<String, Int> = Pair(this, int)
