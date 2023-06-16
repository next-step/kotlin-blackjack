package dsl

fun introduce(init: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block = init).build()
