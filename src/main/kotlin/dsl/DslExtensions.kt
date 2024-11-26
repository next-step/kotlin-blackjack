package dsl

fun introduce(block: Person.Builder.() -> Unit): Person {
    return Person.Builder().apply(block).build()
}
