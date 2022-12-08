package study

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): List<Skill> {
        return skills
    }
}

sealed class Skill(open val value: String)
data class SoftSkill(override val value: String) : Skill(value)
data class HardSkill(override val value: String) : Skill(value)
