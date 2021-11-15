package resume.domain

fun introduce(initializer: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initializer).build()
}
