package study

class SkillBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}