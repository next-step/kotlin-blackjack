package dslstudy

fun introduce(
    initializer: Person.() -> Unit
): Person {
    return Person().apply(initializer)
}
