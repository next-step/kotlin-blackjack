package dsl

data class Person(
    val name: String,
    val company: String,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person = Person(name, company)
}
