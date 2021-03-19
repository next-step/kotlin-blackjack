package study

class Person private constructor(
    val name: String,
    val company: String,
    val languages: Languages,
    val skills: Skills
) {
    class Builder {
        lateinit var name: String
        lateinit var company: String
        lateinit var languages: Languages
        lateinit var skills: Skills

        fun name(name: String) {
            this.name = name
        }

        fun company(company: String) {
            this.company = company
        }

        fun languages(lambda: Languages.Builder.() -> Unit) {
            languages = Languages.Builder().apply(lambda).build()
        }

        fun skills(lambda: Skills.Builder.() -> Unit) {
            skills = Skills.Builder().apply(lambda).build()
        }

        fun build(): Person {
            return Person(name, company, languages, skills)
        }
    }
}
