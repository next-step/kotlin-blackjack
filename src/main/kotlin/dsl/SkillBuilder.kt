package dsl

class SkillBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
