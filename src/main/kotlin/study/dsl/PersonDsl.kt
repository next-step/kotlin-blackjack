package study.dsl

import study.Person
import study.builder.PersonBuilder

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
