package study

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Languages
) {

    class Builder(private val name: String) {
        var company: String? = null
        var skills: Skills = Skills.empty()
        var languages: Languages = Languages.empty()

        fun skills(initializer: Skills.() -> Unit): Builder = apply {
            this.skills = Skills().apply(initializer)
        }

        fun languages(initializer: Languages.() -> Unit): Builder = apply {
            this.languages = Languages().apply(initializer)
        }

        fun build(): Person = Person(
            name = name,
            company = company,
            skills = skills,
            languages = languages
        )
    }
}

fun introduce(
    name: String,
    initializer: Person.Builder.() -> Unit = { }
): Person = Person.Builder(name).apply(initializer).build()
