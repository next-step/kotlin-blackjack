package dsl.performer

data class Skills(
    val soft: List<String>,
    val hard: List<String>,

) {
    override fun toString(): String {
        return "Skills(soft=$soft, hard=$hard)"
    }
}

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
