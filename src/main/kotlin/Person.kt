data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
) {
    class Builder {
        private lateinit var name: String
        private var company: String? = null
        private var skills: Skills? = null
        private var languages: Languages? = null

        fun name(value: String) {
            name = value
        }

        fun company(value: String) {
            company = value
        }

        fun skills(block: Skills.Builder.() -> Unit) {
            skills = Skills.Builder().apply(block).build()
        }

        fun languages(block: Languages.Builder.() -> Unit) {
            languages = Languages.Builder().apply(block).build()
        }

        fun build(): Person {
            return Person(name, company, skills, languages)
        }
    }
}