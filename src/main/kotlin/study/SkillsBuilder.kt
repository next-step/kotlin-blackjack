package study

class SkillsBuilder {
    private val hardSkills = mutableListOf<Skill.Hard>()
    private val softSkills = mutableListOf<Skill.Soft>()

    fun hard(value: String) {
        hardSkills.add(Skill.Hard(value))
    }

    fun soft(value: String) {
        softSkills.add(Skill.Soft(value))
    }

    fun build() = Skills(hardSkills, softSkills)
}
