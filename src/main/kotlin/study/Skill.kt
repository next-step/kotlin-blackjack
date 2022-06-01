package study

data class Skill(
    val name: String,
    val type: SkillType
)

enum class SkillType {
    SOFT,
    HARD
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(name: String) {
        skills.add(Skill(name, SkillType.SOFT))
    }

    fun hard(name: String) {
        skills.add(Skill(name, SkillType.HARD))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
