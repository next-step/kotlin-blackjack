package dsl

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}
