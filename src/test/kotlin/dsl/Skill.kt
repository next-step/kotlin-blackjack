package dsl

class Skills(
    val softSkills: List<SoftSkill>,
    val hardSkills: List<HardSkill>
)
sealed class Skill(val name: String) {
    fun isSoft(): Boolean {
        return this is SoftSkill
    }

    fun isHard(): Boolean {
        return this is HardSkill
    }
}

class SoftSkill(name: String) : Skill(name)
class HardSkill(name: String) : Skill(name)

class SkillBuilder {
    private val softSkills = mutableListOf<SoftSkill>()
    private val hardSkills = mutableListOf<HardSkill>()

    fun soft(skill: String) {
        softSkills.add(SoftSkill(skill))
    }

    fun hard(skill: String) {
        hardSkills.add(HardSkill(skill))
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}
