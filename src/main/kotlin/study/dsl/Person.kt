package study.dsl

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)

data class Languages(val languages: Map<String, Int>)
