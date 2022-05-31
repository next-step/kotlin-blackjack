package dsl.domain

/**
 * Created by Jaesungchi on 2022.05.31..
 */
class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String

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
