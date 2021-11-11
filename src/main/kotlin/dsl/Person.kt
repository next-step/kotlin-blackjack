package dsl

class Person {
    lateinit var name: String

    fun name(name: String) = name.also { this.name = it }
}

fun introduce(initializer: Person.() -> Unit): Person {
    val person = Person()
    person.initializer()
    return person
}
