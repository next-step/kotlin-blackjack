package personDsl

data class Person(
    val name: String,
    val company: String?,
    val softSkills: List<String>,
    val hardSkills: List<String>,
)
