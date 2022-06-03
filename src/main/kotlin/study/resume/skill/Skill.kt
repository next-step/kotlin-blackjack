package study.resume.skill

sealed class Skill

data class SoftSkill(val desc: String) : Skill()

data class HardSkill(val desc: String) : Skill()

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()
    fun soft(desc: String) {
        skills.add(SoftSkill(desc))
    }

    fun hard(desc: String) {
        skills.add(HardSkill(desc))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
