package resume

fun introduce(init: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(init).build()

class PersonBuilder {
    lateinit var name: String
    var company: String? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build() = Person(name, company)
}
