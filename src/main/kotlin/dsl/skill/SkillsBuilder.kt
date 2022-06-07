package dsl.skill

class SkillsBuilder {
    private var skills: Skills = Skills()

    fun soft(value: String) {
        skills.add(Skill.Soft(value))
    }

    fun hard(value: String) {
        skills.add(Skill.Hard(value))
    }

    fun build(): Skills {
        return skills
    }
}
