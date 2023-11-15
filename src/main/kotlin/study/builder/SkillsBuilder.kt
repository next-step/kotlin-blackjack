package study.builder

import study.dto.Skill

class SkillsBuilder {
    private var skills: List<Skill> = emptyList()
    fun soft(description: String) {
        addSkill(description)
    }

    fun hard(description: String) {
        addSkill(description)
    }

    fun build(): List<Skill> {
        return skills
    }

    private fun addSkill(description: String) {
        skills = skills + Skill(description)
    }
}
