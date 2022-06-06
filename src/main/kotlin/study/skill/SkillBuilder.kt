package study.skill

class SkillBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skill {
        return Skill(soft.toList(), hard.toList())
    }
}
