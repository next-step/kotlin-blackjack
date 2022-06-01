package com.nextstep.jngcii.dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String
)

class PersonBuilder {
    var name: String = ""
    var company: String? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build() = Person(
        name = name,
        company = company ?: "무직"
    )
}
