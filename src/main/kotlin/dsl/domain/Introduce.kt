package dsl.domain

import dsl.builder.PersonBuilder

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder(block).build()
}
