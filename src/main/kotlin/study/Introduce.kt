package study

fun introduce(initializer: Person.Builder.() -> Unit): Person {
    return Person.Builder().apply(initializer).build()
}
