package dsl

import dsl.Skill.Companion.hardSkill
import dsl.Skill.Companion.softSkill

class Skills(
    val values: List<Skill>,
)

class SkillsBuilder {
    private val values: MutableList<Skill> = mutableListOf()

    fun soft(information: String) = values.add(softSkill(information))

    fun hard(information: String) = values.add(hardSkill(information))

    fun build(): Skills {
        return Skills(values.toList())
    }
}
