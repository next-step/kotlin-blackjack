package study

class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build() = Skills(soft, hard)
}

data class Skills(val soft: List<String>, val hard: List<String>)
