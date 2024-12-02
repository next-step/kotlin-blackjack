package study

data class Skill(val type: SkillType, val description: String)

enum class SkillType {
    SOFT,
    HARD,
}

@DslScope
class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(Skill(SkillType.SOFT, description))
    }

    fun hard(description: String) {
        skills.add(Skill(SkillType.HARD, description))
    }

    fun build(): List<Skill> = skills
}
