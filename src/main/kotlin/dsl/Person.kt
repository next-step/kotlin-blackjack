package dsl

class Person {
    lateinit var name: Name
    lateinit var company: Company

    fun name(name: String) = name.also { this.name = Name(it) }
    fun company(company: String) = company.also { this.company = Company(it) }
}

fun introduce(initializer: Person.() -> Unit): Person {
    val person = Person()
    person.initializer()
    return person
}
