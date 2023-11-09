package dsl.performer

class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()
    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

