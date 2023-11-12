package dsl

class SkillBuilder(
    private var soft: MutableList<Skill> = mutableListOf(),
    private var hard: MutableList<Skill> = mutableListOf()
) {

    fun soft(skill: Skill) {
        soft.add(skill)
    }

    fun hard(skill: Skill) {
        hard.add(skill)
    }

    fun build(): Skills = Skills(
        soft = soft,
        hard = hard,
    )
}

fun introduceSkill(block: SkillBuilder.() -> Unit): Skills {
    return SkillBuilder().apply(block).build()
}
