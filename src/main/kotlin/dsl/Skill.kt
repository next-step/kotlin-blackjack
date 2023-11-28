package dsl

sealed class Skill(
    open val value: String
) {
    data class Soft(override val value: String) : Skill(value)
    data class Hard(override val value: String) : Skill(value)
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill.Soft(value))
    }

    fun hard(value: String) {
        skills.add(Skill.Hard(value))
    }

    fun build(): List<Skill> = skills
}