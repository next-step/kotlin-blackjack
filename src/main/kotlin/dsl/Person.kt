package dsl

import dsl.builder.PersonBuilder

data class Person(
    val name: String,
    val company: String?,
    val skills: List<String> = emptyList(),
    val languages: Map<String, Int> = emptyMap(),
) {
    companion object {
        fun introduce(function: PersonBuilder.() -> Unit): Person {
            return PersonBuilder().apply(function).build()
        }
    }
}
