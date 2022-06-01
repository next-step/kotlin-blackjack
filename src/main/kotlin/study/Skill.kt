package study

sealed interface Skill

data class SoftSkill(val name: String) : Skill
data class HardSkill(val name: String) : Skill

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(name: String) {
        skills.add(SoftSkill(name))
    }

    fun hard(name: String) {
        skills.add(HardSkill(name))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
