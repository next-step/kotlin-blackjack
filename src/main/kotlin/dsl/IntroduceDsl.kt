package dsl

fun introduce(block: DeveloperBuilder.() -> Unit): Developer = DeveloperBuilder().apply(block).build()
