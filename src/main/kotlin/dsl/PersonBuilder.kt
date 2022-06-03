package dsl

class PersonBuilder {
    var name: String = ""

    fun name(value: String) {
        name = value
    }

    fun build(): Person {
        return Person(
            name = name
        )
    }
}


fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block)
        .build()
}
