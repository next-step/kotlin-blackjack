package camp.nextstep.study

class SkillsBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills += Skill(Skill.SkillType.SOFT, description)
    }

    fun hard(description: String) {
        skills += Skill(Skill.SkillType.HARD, description)
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
