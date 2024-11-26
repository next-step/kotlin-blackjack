package dsl

class Skills {
    private val _skills = mutableListOf<Skill>()
    val skills get() = _skills.toList()

    fun soft(description: String) {
        _skills.add(Skill.SoftSkill(description))
    }

    fun hard(description: String) {
        _skills.add(Skill.HardSkill(description))
    }

    override fun toString(): String {
        return skills.joinToString("\n") {
            when (it) {
                is Skill.SoftSkill -> "soft skill: ${it.description}"
                is Skill.HardSkill -> "hard skill: ${it.description}"
            }
        }
    }
}
