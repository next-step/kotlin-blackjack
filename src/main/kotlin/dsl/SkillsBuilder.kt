package dsl

class SkillsBuilder(
    private val skills: MutableList<Skill> = mutableListOf()
) {
    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): Skills {
        return Skills(skills.toList())
    }
}

data class Skills(val skills: List<Skill> = emptyList())
sealed class Skill(open val name: String)

data class SoftSkill(override val name: String) : Skill(name)
data class HardSkill(override val name: String) : Skill(name)
