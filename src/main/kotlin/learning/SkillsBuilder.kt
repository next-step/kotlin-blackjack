package learning

class SkillsBuilder {
    val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill(SkillType.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(SkillType.HARD, value))
    }
}
