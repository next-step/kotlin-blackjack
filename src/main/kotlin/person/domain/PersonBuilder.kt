package person.domain

class PersonBuilder {
    private lateinit var name: String

    fun name(value: String) {
        name = value
    }

    fun build(): Person = Person(name)
}
