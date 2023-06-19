package dsl.builder

import dsl.domain.Skill
import dsl.domain.SkillType
import dsl.domain.Skills

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill(description, SkillType.SOFT))
    }

    fun hard(description: String) {
        skills.add(Skill(description, SkillType.HARD))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
