package dsl

data class Skills(val soft: List<String>, var hard: List<String>)

class SkillsBuilder {

    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(value: String) {
        this.soft.add(value)
    }

    fun hard(value: String) {
        this.hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft.toList(), hard.toList())
    }
}
