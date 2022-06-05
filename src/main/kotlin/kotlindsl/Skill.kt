package kotlindsl

sealed class Skill(open val name: String) {
    data class Hard(override val name: String) : Skill(name)
    data class Soft(override val name: String) : Skill(name)
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(name: String) {
        skills.add(Skill.Soft(name))
    }

    fun hard(name: String) {
        skills.add(Skill.Hard(name))
    }

    fun build(): List<Skill> = skills.toList()
}
