package study

data class Skill(val type: Type, val description: String) {
    enum class Type {
        SOFT, HARD
    }

    companion object {
        fun soft(description: String) = Skill(Type.SOFT, description)
        fun hard(description: String) = Skill(Type.HARD, description)
    }
}

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()
    fun soft(description: String) {
        val skill = Skill.soft(description)
        skills.add(skill)
    }

    fun hard(description: String) {
        val skill = Skill.hard(description)
        skills.add(skill)
    }

    fun build() = skills.toList()
}
