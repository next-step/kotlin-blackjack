package study

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder().apply(block).build()
