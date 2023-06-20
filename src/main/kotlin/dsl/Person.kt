package dsl

data class Person(val name: String)

class PersonBuilder {

    private lateinit var name: String

    fun name(value: String) {
        this.name = value
    }

    fun build(): Person {
        return Person(name)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
