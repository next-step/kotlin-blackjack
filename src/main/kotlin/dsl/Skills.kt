package dsl

class Skills(val soft: List<String>, val hard: List<String>)

class SkillsBuilder {

    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(skill: String) {
        soft.add(skill)
    }

    fun hard(skill: String) {
        hard.add(skill)
    }

    fun builder(): Skills {
        return Skills(soft, hard)
    }
}
