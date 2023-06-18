package dsl.skill

import dsl.skill.Skill.Companion.hardSkill
import dsl.skill.Skill.Companion.softSkill

@JvmInline
value class Skills(
    val values: List<Skill> = listOf(),
)

class SkillsBuilder {
    private val values: MutableList<Skill> = mutableListOf()

    fun soft(information: String) = values.add(softSkill(information))

    fun hard(information: String) = values.add(hardSkill(information))

    fun build(): Skills {
        return Skills(values.toList())
    }
}
