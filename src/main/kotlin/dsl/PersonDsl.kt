package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

fun PersonBuilder.skills(block: PersonBuilder.() -> Unit): Person {
    return apply(block).build()
}
