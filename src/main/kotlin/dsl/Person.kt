package dsl

class Person {
    lateinit var name: String
    lateinit var company: String

    fun name(name: String) = name.also { this.name = it }
    fun company(company: String) = company.also { this.company = it }
}

fun introduce(initializer: Person.() -> Unit): Person {
    val person = Person()
    person.initializer()
    return person
}
