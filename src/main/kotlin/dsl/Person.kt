package dsl

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: Map<String, Int>
)
