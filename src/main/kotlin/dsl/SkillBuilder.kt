package dsl

class SkillBuilder {

    private var skills: MutableList<Skill> = mutableListOf()

    fun soft(skill: String) {
        Skill.Soft(skill).also { skills.add(it) }
    }

    fun hard(skill: String) {
        Skill.Hard(skill).also { skills.add(it) }
    }

    fun build(): List<Skill> = skills
}
