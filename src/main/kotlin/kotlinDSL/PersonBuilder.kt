package kotlinDSL

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}