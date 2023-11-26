package dsl.domain

class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(skill: String) {
        soft.add(skill)
    }

    fun hard(skill: String) {
        hard.add(skill)
    }

    fun build(): Skills {
        return Skills(soft.toList(), hard.toList())
    }
}
