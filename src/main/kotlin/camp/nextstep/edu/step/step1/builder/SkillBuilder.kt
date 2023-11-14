package camp.nextstep.edu.step.step1.builder

import camp.nextstep.edu.step.step1.domain.Skill

class SkillBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill.SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(Skill.HardSkill(value))
    }

    fun build(): List<Skill> = skills

}
