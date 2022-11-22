package step1

sealed class Skill(
    val type: String,
    val value: String,
) {
    class Hard(value: String) : Skill("hard", value)
    class Soft(value: String) : Skill("soft", value)
}

data class Skills(
    val skills: List<Skill>
)

class SkillBuilder(
) {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill.Soft(value))
    }

    fun hard(value: String) {
        skills.add(Skill.Hard(value))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
