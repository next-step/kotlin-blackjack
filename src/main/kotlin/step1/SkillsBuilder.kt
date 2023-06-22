package step1

class SkillsBuilder {
    private val softSkills: MutableList<Skill> = mutableListOf()
    private val hardSkills: MutableList<Skill> = mutableListOf()

    fun soft(skill: Skill) = softSkills.add(skill)

    fun hard(skill: Skill) = softSkills.add(skill)

    fun build() = Skils(soft = softSkills, hard = hardSkills)
}