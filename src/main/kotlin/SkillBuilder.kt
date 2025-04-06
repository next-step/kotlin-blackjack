import models.Skill

class SkillBuilder {
    private var _skills = mutableListOf<Skill>()
    private val skills = _skills

    fun soft(description: String) {
        _skills.add(Skill(type = "soft", description = description))
    }

    fun hard(description: String) {
        _skills.add(Skill(type = "hard", description = description))
    }

    fun build(): List<Skill> = skills
}