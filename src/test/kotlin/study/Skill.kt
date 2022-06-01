package study

sealed class Skill

data class HardSkill(val description: String) : Skill()

data class SoftSkill(val description: String) : Skill()

@JvmInline
value class Skills(private val skills: List<Skill> = emptyList())

class SkillBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): Skills = Skills(skills)
}
