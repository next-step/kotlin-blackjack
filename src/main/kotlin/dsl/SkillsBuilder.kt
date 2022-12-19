package dsl

class SkillsBuilder {
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