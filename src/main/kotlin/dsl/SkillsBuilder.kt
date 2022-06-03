package dsl

class SkillsBuilder {
    private val soft = mutableListOf<Skill>()
    private val hard = mutableListOf<Skill>()

    fun soft(title: String) {
        soft.add(Skill(title))
    }

    fun hard(title: String) {
        hard.add(Skill(title))
    }

    fun build(): Skills {
        return Skills(soft = soft.toList(), hard = hard.toList())
    }
}
