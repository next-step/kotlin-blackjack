package studydsl

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build() = Skills(skills)
}
