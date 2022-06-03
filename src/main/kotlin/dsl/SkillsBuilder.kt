package dsl

class SkillsBuilder {
    private val soft: MutableList<Skill> = mutableListOf()
    private val hard: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        soft.add(Skill(value))
    }

    fun hard(value: String) {
        hard.add(Skill(value))
    }

    fun build(): Skills {
        return Skills(
            soft = soft.toList(),
            hard = hard.toList()
        )
    }
}
