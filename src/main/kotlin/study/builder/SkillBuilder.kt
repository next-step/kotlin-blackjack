package study.builder

import study.HardSkill
import study.Skill
import study.SoftSkill

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        val skill = SoftSkill(value)
        skills.add(skill)
    }

    fun hard(value: String) {
        val skill = HardSkill(value)
        skills.add(skill)
    }

    fun build() = skills
}
