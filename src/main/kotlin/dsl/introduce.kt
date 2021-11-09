package dsl

fun introduce(init: Person.() -> Unit): Person {
    return Person().apply {
        init()
    }
}
