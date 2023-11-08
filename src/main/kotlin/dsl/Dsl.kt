package dsl

fun introduce(
    name: String = "이름 없는 유저",
    block: PersonBuilder.() -> Unit
): Person = PersonBuilder(name).apply(block).build()
