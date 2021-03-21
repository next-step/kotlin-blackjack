package study

fun introduce(initialize: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initialize).build()
}
