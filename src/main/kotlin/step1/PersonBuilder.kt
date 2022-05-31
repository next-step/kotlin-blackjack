package step1

class PersonBuilder {
    private var name: String = ""

    fun name(value: String) {
        this.name = value
    }

    fun build(): Person {
        return Person(name)
    }
}
