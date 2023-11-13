package kotlinDSL

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: List<Language>
)
