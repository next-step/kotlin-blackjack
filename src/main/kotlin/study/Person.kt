package study

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

data class Person(val name: String, val company: String?, val skills: Skills?)
