package dsl

class PersonBuilder {

    private lateinit var name: String

    fun name(name: String) {
        this.name = name
    }

    fun build(): Person = Person(
        name = name,
    )
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
