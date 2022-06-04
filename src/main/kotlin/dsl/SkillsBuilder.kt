package dsl

class SkillsBuilder {
    private val skills = Skills()

    fun soft(value: String) {
        skills.soft(Skill(value))
    }

    fun hard(value: String) {
        skills.hard(Skill(value))
    }

    fun build(): Skills {
        return skills
    }
}
