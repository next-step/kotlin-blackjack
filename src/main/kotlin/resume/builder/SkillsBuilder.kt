package resume.builder

import resume.model.Skill
import resume.model.Skills

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(SoftSkill(description))
    }

    fun hard(description: String) {
        skills.add(HardSkill(description))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}

class HardSkill(description: String) : Skill(description)
class SoftSkill(description: String) : Skill(description)
