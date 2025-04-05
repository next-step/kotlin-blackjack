package dsl

data class Person(
    var name: String = "",
    var company: String = "",
) {
    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }
}

class PersonBuilder {
    private lateinit var name: String

    fun name(name: String) {
        this.name = name
    }

    fun build() = Person(name)
}

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}
