package dsl

fun introduce(block: Person.() -> Unit): Person = Person().apply(block)

class Person {
    lateinit var name: String
    lateinit var company: String

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }
}
