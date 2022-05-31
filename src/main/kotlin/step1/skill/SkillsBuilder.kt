package step1.skill

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill(Level.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(Level.HARD, value))
    }

    fun build() = Skills(skills)
}
