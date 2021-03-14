package study

fun introduce(initialize: Person.() -> Unit): Person {
    return Person().apply(initialize)
}
