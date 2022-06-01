package com.nextstep.jngcii.dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String
)

class PersonBuilder {
    var name: String = ""

    fun name(value: String) {
        name = value
    }

    fun build() = Person(
        name = name
    )
}
