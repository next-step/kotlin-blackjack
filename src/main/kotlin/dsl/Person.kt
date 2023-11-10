package dsl

data class Person(
    val name: String,
    val company: String? = null,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList()
) {
    init {
        require(name.isNotBlank()) { "이름은 공백이 아니어야 합니다." }
    }
}
