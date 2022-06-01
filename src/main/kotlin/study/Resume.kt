package study

class Resume(
    val name: String,
    val company: String? = null,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList(),
)
