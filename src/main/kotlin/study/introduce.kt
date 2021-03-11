package study

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}