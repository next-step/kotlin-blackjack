package dsl.skill

class SkillsBuilder {
    private val softSkills: MutableList<Skill> = emptyList<Skill>().toMutableList()
    private val hardSkills: MutableList<Skill> = emptyList<Skill>().toMutableList()

    fun soft(description: String) {
        softSkills.add(SoftSkill(description = description))
    }

    fun hard(description: String) {
        hardSkills.add(HardSkill(description = description))
    }

    fun build(): Skills = Skills(softSkills, hardSkills)
}
