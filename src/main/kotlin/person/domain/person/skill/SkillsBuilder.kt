package person.domain.person.skill

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun skills(block: SkillsBuilder.() -> Unit): Skills {
        return SkillsBuilder().apply(block).build()
    }

    fun soft(value: String) {
        skills.add(Skill.SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(Skill.HardSkill(value))
    }

    private fun build(): Skills {
        return Skills(skills)
    }
}
