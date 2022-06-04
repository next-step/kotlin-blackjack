package dsl

data class Person(
    val name: String,
    val company: String,
    val languages: Languages,
)

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var languages: Languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder()
            .apply(block)
            .build()
    }

    fun build(): Person = Person(
        name,
        company,
        languages,
    )
}
