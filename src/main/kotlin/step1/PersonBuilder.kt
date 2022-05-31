package step1

class PersonBuilder {
    private var name: String = DEFAULT_NAME

    fun name(value: String) {
        this.name = value
    }

    fun build(): Person {
        return Person(name)
    }

    companion object {
        const val DEFAULT_NAME: String = ""
    }
}
