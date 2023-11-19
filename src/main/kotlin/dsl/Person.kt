package dsl

class PersonBuilder {
    lateinit var name: String
    var company: String = ""

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

class Person(val name: String, val company: String? = "") {
}
