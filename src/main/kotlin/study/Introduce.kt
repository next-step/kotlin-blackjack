package study

fun introduce(name: String, block: PersonBuilder.() -> Unit = { }): Person {
    return PersonBuilder(name).apply(block).build()
}
