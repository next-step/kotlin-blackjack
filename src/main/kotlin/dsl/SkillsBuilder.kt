package dsl

class SkillsBuilder {
    private var skills = Skills(emptyList())

    fun soft(skill: String) {
        skills.add(Skill.Soft(skill))
    }

    fun hard(skill: String) {
        skills.add(Skill.Hard(skill))
    }

    fun build(): Skills {
        return skills
    }
}
