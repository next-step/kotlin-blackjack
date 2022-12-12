package study

fun introduce(block: PersonBuilder.() -> Unit) = PersonBuilder().apply(block).build()

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build() = Person(name, company)
}

data class Person(val name: String, val company: String)
