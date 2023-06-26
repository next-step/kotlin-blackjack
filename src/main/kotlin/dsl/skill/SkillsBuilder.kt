package dsl.skill

class SkillsBuilder {
    lateinit var skillType: SkillType
    lateinit var description: String
    private val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        createSKill(SkillType.SOFT, description)
    }

    fun hard(description: String) {
        createSKill(SkillType.HARD, description)
    }

    private fun createSKill(skillType: SkillType, description: String) {
        this.skillType = skillType
        this.description = description
        skills.add(Skill(this.skillType, this.description))
    }

    fun build(): List<Skill> {
        return skills.deepCopy()
    }
}
