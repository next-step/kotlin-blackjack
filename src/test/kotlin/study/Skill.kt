package study

data class Skill(val name: String, val level: SkillLevel)

class SkillBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill(value, SkillLevel.SOFT))
    }

    fun hard(value: String) {
        skills.add(Skill(value, SkillLevel.HARD))
    }

    fun build(): MutableList<Skill> {
        val result = skills.map { it.copy() }
        skills.clear()
        return result.toMutableList()
    }
}

enum class SkillLevel(val level: String) {
    SOFT("SOFT"),
    HARD("HARD"),
}
