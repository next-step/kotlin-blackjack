package dsl

import dsl.domain.Person
import dsl.domain.PersonBuilder

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
