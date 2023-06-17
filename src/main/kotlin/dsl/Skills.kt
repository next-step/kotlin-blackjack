package dsl

data class Skills(
    val softSkills: List<String>,
    val hardSkills: List<String>,
)

class SkillsBuilder {
    private val softSkills: MutableList<String> = mutableListOf()
    private val hardSkills: MutableList<String> = mutableListOf()

    fun soft(softSkill: String) {
        softSkills.add(softSkill)
    }

    fun hard(hardSkill: String) {
        hardSkills.add(hardSkill)
    }

    fun build() = Skills(softSkills, hardSkills)
}

fun introduceSkills(block: SkillsBuilder.() -> Unit): Skills {
    return SkillsBuilder().apply(block).build()
}
