package study

class SkillBuilder {
    private var skills: MutableList<study.skill.Skill> = mutableListOf()

    fun hard(value: String) {
        this.skills.add(study.skill.Hard(value))
    }

    fun soft(value: String) {
        this.skills.add(study.skill.Soft(value))
    }

    fun build(): MutableList<study.skill.Skill> {
        return skills
    }
}
