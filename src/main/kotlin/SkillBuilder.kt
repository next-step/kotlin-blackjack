import models.Skill
import models.SkillType

class SkillBuilder {
    private var _skills = mutableListOf<Skill>()

    fun soft(description: String) {
        _skills.add(Skill(type = SkillType.SOFT, description = description))
    }

    fun hard(description: String) {
        _skills.add(Skill(type = SkillType.HARD, description = description))
    }

    fun build(): List<Skill> = _skills
}

