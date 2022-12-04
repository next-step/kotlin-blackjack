package study

class SkillsBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

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
