package dsl

data class Person(
    val name: String,
    val company: String?
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun build() = Person(name, company)
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
