package study

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()
    fun soft(desc: String) = skills.add(Skill(SOFT_SKILL, desc))
    fun hard(desc: String) = skills.add(Skill(HARD_SKILL, desc))
    fun build(): List<Skill> = skills

    companion object {
        private const val SOFT_SKILL = "soft"
        private const val HARD_SKILL = "hard"
    }
}
