package dsl

import dsl.person.Person
import dsl.person.PersonBuilder

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
