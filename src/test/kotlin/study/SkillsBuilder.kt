package study

class SkillsBuilder {
    private var soft: MutableList<String> = mutableListOf()
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
