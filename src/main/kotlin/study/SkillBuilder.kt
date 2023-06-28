package study

class SkillBuilder : Builder<List<Skill>> {
    private var skills: List<Skill> = emptyList()

    fun soft(value: String) {
        skills += Soft(value)
    }

    fun hard(value: String) {
        skills += Hard(value)
    }

    override fun build(): List<Skill> {
        return skills.toList()
    }
}
