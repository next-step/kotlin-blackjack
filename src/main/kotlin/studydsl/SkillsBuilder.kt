package studydsl

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill.SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(Skill.HardSkill(value))
    }

    fun build() = Skills(skills)
}
