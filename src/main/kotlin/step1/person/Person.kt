package step1.person

import step1.language.Languages
import step1.skill.Skills

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Languages
)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
