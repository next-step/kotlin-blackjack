package dsl.skill

class SkillsBuilder {
    private var skills: Skills = Skills()

    fun soft(value: String) {
        skills.add(Skill(SkillLevel.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(SkillLevel.HARD, value))
    }

    fun build(): Skills {
        return skills
    }
}
