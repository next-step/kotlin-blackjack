package study

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill(SkillType.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(SkillType.HARD, value))
    }

    fun build() = skills
}

data class Skill(
    val type: SkillType,
    val description: String,
)

enum class SkillType {
    SOFT, HARD
}
