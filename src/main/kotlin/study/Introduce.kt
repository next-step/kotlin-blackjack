package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    // also, apply, run, let, with
    return PersonBuilder().apply(block).build()
}
