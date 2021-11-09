package dsl

import dsl.person.Person
import dsl.person.PersonBuilder

fun introduce(init: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(init).build()
}
