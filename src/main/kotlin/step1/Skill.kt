package step1

data class Skill(
    val soft: List<String>,
    val hard: List<String>,
)

class SkillBuilder(
    val soft: MutableList<String> = mutableListOf(),
    val hard: MutableList<String> = mutableListOf(),
) {
    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skill {
        return Skill(soft, hard)
    }
}
