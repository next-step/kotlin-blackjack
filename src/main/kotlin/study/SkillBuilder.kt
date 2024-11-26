package study

class SkillBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(Skill.Soft(description))
    }

    fun hard(description: String) {
        skills.add(Skill.Hard(description))
    }

    fun build(): List<Skill> = skills
}
