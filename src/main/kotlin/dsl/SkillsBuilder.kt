package dsl

import dsl.vo.Skill

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill(value))
    }

    fun hard(value: String) {
        skills.add(Skill(value))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
