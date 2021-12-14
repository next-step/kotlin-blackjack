package study

fun introduce(initializer: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initializer).build()
}
