package study

interface Skill
data class SoftSkill(val value: String) : Skill
data class HardSkill(val value: String) : Skill

class SkillBuilder {

    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
