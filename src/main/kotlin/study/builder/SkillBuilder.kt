package study.builder

import study.HardSkill
import study.Skill
import study.SoftSkill

class SkillBuilder {
    private var skills: List<Skill> = listOf()

    fun soft(value: String) {
        val skill = SoftSkill(value)
        skills += skill
    }

    fun hard(value: String) {
        val skill = HardSkill(value)
        skills += skill
    }

    fun build() = skills
}
