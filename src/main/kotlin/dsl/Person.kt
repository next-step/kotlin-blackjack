package dsl

class Person private constructor(
    val name: String?,
    val company: String?,
    val skills: Skills,
    val languages: Languages,
) {
    override fun toString(): String {
        return StringBuilder().apply {
            if (!name.isNullOrEmpty()) {
                append("name: $name\n")
            }
            if (!company.isNullOrEmpty()) {
                append("company: $company")
            }
            append(skills)
            append(languages)
        }.toString()
    }

    class Builder {
        private var name: String? = null
        private var company: String? = null
        private val skills: Skills by lazy { Skills() }
        private val languages: Languages by lazy { Languages() }

        fun name(value: String) {
            this.name = value
        }

        fun company(value: String) {
            this.company = value
        }

        fun skills(block: Skills.() -> Unit) {
            this.skills.apply(block)
        }

        fun languages(block: Languages.() -> Unit) {
            this.languages.apply(block)
        }

        fun build(): Person {
            return Person(
                this.name,
                this.company,
                this.skills,
                this.languages,
            )
        }
    }
}
