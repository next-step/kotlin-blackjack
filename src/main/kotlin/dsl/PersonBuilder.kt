package dsl

class PersonBuilder {
    var name: String = ""
    var company: String = ""

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(
            name = name,
            company = company
        )
    }
}


fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block)
        .build()
}
