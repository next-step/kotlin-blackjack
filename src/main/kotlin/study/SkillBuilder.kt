package study

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill(description))
    }

    fun hard(description: String) {
        skills.add(Skill(description))
    }

    fun build(): List<Skill> = skills
}
