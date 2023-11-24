package study.model

/**
 * 사람 객체
 * */
data class Person(
    val name: String,
    val company: String,
    val skills: List<Pair<String, String>>,
    val languages: Map<String, Int>
)
