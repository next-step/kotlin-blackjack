package dsl.builder

import dsl.domain.Hard
import dsl.domain.Skill
import dsl.domain.Skills
import dsl.domain.Soft

class SkillsBuilder(block: SkillsBuilder.() -> Unit) {
    private val skills: MutableList<Skill> = mutableListOf()

    init {
        apply(block)
    }

    fun soft(description: String) {
        skills.add(Soft(description))
    }

    fun hard(description: String) {
        skills.add(Hard(description))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
