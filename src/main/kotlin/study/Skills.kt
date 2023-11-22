package study

class Skills(val soft: List<Skill>, val hard: List<Skill>)

class SkillsBuilder {
    private val soft = mutableListOf<Skill>()
    private val hard = mutableListOf<Skill>()

    fun soft(value: Skill) {
        soft.add(value)
    }

    fun hard(value: Skill) {
        hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft = soft, hard = hard)
    }
}

private typealias Skill = String
