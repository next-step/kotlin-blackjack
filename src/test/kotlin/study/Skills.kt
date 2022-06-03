package study

data class Skills(val soft: List<String>, val hard: String)

class SkillsBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private lateinit var hard: String

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard = value
    }

    fun build(): Skills {
        return Skills(soft.toList(), hard)
    }
}
