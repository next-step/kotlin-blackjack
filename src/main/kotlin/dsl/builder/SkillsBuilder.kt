package dsl.builder

import dsl.model.Skill
import dsl.model.SkillType
import dsl.model.Skills

class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()
    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills {
        val softSkills: MutableList<Skill> = soft.map {
            Skill(SkillType.SOFT, it)
        }.toMutableList()
        val hardSkills = hard.map {
            Skill(SkillType.HARD, it)
        }
        softSkills.addAll(hardSkills)
        return Skills(softSkills)
    }
}
