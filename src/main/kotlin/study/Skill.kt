package study

data class Skill(val description: String)

class SkillBuilder {
    private var skills: MutableList<Skill> = emptyList<Skill>().toMutableList()

    fun soft(value: String) {
        skills.add(Skill(value))
    }

    fun hard(value: String) {
        skills.add(Skill(value))
    }

    fun build(): List<Skill> {
        return skills
    }
}
