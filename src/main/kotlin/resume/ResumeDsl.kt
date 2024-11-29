package resume

fun introduce(init: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(init).build()

@ResumeMarker
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(init: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(init).build()
    }

    fun build() = Person(name, company, skills)
}

@ResumeMarker
class SkillsBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build() = Skills(skills)
}
