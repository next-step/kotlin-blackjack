package study

data class Skill(val softSkills: List<String>, val hardSkills: List<String>)

class SkillBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skill {
        return Skill(softSkills, hardSkills)
    }
}
