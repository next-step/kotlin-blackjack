package dsl.domain

/**
 * Created by Jaesungchi on 2022.05.31..
 */
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skill? = null

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
