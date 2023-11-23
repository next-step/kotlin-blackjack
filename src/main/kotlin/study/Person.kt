package study

class PersonBuilder() {
    private var name: String = ""
    private var company: String = ""
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

data class Person(val name: String, val company: String)
