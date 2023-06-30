package study

class Person(
    val name: String,
    val company: String? = null,
    val skills: Skills = Skills(emptyList(), emptyList()),
    val languages: List<Language> = emptyList()

)
