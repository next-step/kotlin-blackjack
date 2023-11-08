package dsl

fun introduce(
    block: PersonBuilder.() -> Unit
): Person = PersonBuilder().apply(block).build()
