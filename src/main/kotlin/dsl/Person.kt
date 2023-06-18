package dsl

data class Person(
    val name: String,
    val company: String?,
    val languages: Languages?,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, languages)
    }
}
