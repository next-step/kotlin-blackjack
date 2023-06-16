package dsl

@JvmInline
value class Skills(private val skills: List<Skill>) : List<Skill> by skills

data class Skill(val skillLevel: SkillLevel, val description: String)

class SkillBuilder : DslBuilder<Skills>() {

    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) = skills.add(
        element = Skill(skillLevel = SkillLevel.SOFT, description = value),
    )

    fun hard(value: String) = skills.add(
        element = Skill(skillLevel = SkillLevel.HARD, description = value),
    )

    override fun build(): Skills = Skills(skills = skills.toList())
}

enum class SkillLevel {
    SOFT,
    HARD,
    ;
}
