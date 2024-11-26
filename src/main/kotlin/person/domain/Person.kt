package person.domain

class Person(
    val name: String,
    val company: String?,
    val hardSkills: List<String>,
    val softSkills: List<String>,
    val languages: Map<String, Int>,
)
