package personDsl

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

class PersonBuilder : SkillsBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val softSkills: MutableList<String> = mutableListOf()
    private val hardSkills: MutableList<String> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        block()
    }

    override fun soft(softSkill: String) {
        softSkills.add(softSkill)
    }

    override fun hard(hardSkill: String) {
        hardSkills.add(hardSkill)
    }

    fun build(): Person = Person(name, company, softSkills)
}

interface SkillsBuilder {
    fun soft(softSkill: String)

    fun hard(hardSkill: String)
}
