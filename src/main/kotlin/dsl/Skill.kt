package dsl

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(desc: String) {
        skills.add(Skill.Soft(desc))
    }

    fun hard(desc: String) {
        skills.add(Skill.Hard(desc))
    }

    fun build(): List<Skill> = skills.toList()
}

sealed interface Skill {
    val desc: String

    data class Soft(override val desc: String) : Skill
    data class Hard(override val desc: String) : Skill
}

enum class SkillType {
    Soft, Hard
}
