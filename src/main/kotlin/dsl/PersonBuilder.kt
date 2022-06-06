package dsl

import dsl.language.Languages
import dsl.language.LanguagesBuilder

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
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

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
