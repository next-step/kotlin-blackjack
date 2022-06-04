package dsl

data class Person(var name: String, var company: String) {
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }
}
