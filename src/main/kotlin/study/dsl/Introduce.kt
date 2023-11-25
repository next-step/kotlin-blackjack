package study.dsl

import study.builder.PersonBuilder
import study.domain.Person

fun introduce(block: PersonBuilder.() -> Unit): Person {
    // also, apply, run, let, with
    return PersonBuilder().apply(block).build()
}
