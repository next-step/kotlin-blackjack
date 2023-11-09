package dsl.performer

data class Person(
    val name: String?,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,

) {
    override fun toString(): String {
        return """
        name=${this.name}\n
        company=${this.company}
        skills=[${this.skills}]
        languages=[${this.languages}]
        """.trimMargin()
    }
}
