package study

sealed class Skill(val value: String) {
    init {

    }

    class SoftSkill(value: String) : Skill(value)
    class HardSkill(value: String) : Skill(value)
}

class SkillBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill.SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(Skill.HardSkill(value))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
