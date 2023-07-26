package learning

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill(SkillType.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(SkillType.HARD, value))
    }

    fun build(): MutableList<Skill> {
        return skills
    }
}
