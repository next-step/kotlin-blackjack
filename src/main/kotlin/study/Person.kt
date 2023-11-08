package study

data class Person(
    val name: String,
    val company: String = "",
    val skills: Skills = Skills(),
    val languages: Languages = Languages(),
)
