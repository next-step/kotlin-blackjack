package introduce.domain

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}
