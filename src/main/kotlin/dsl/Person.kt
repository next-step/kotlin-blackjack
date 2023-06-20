package dsl

data class Person(val name: String, val company: String? = null)

class PersonBuilder {

    private lateinit var name: String
    private var company: String? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
