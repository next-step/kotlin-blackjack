package dsl

fun introduce(init: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(init).build()
}
