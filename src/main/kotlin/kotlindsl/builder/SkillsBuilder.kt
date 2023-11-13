package kotlindsl.builder

import kotlindsl.enum.SkillType
import kotlindsl.model.Skill

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(content: String) = setSkills(SkillType.Hard, content)

    fun soft(content: String) = setSkills(SkillType.Soft, content)

    private fun setSkills(skill: SkillType, content: String) {
        skills.add(Skill(skill, content))
    }

    fun build(): List<Skill> = this.skills
}
