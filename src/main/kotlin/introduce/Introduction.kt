package introduce

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()
