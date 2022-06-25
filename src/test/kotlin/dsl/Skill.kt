package dsl

data class Skill(
    val softSkills: List<String>,
    val hardSkills: List<String>
)

class SkillBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(skill: String) {
        softSkills.add(skill)
    }

    fun hard(skill: String) {
        hardSkills.add(skill)
    }

    fun build(): Skill {
        return Skill(softSkills, hardSkills)
    }
}
