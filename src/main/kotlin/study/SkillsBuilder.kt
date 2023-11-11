package study

class SkillsBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill(SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(HARD, value))
    }

    fun build(): Skills {
        return Skills(skills)
    }

    companion object {
        private const val SOFT = "soft"
        private const val HARD = "hard"
    }
}
