package study

data class Skills(
    val soft: Set<String>,
    val hard: Set<String>
)

class SkillsBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private val hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft.toSet(), hard.toSet())
    }
}
