package dsl

sealed class Skill

data class SoftSkill(val text: String) : Skill()
data class HardSkill(val text: String) : Skill()

@JvmInline
value class Skills(private val skills: List<Skill> = emptyList())

class SkillBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(text: String) {
        skills.add(SoftSkill(text))
    }

    fun hard(text: String) {
        skills.add(HardSkill(text))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
