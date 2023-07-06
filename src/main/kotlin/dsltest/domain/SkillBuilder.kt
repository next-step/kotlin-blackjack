package dsltest.domain

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()
    fun soft(value: String) {
        skills.add(Skill(value))
    }

    fun hard(value: String) {
        skills.add(Skill(value))
    }

    fun build() = skills
}
