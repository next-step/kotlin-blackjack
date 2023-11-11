package dsl

class PersonBuilder {

    private lateinit var name: String
    private lateinit var company: String

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun build(): Person = Person(
        name = name,
        company = company,
    )
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
