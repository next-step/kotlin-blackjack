package study.dsl

data class Skill(
    val skillType: String,
    val description: String
)

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill(SOFT, description))
    }

    fun hard(description: String) {
        skills.add(Skill(HARD, description))
    }

    fun build(): List<Skill> = skills

    companion object {
        private const val SOFT = "soft"
        private const val HARD = "hard"
    }
}
