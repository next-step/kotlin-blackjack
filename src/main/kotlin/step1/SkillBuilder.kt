package step1

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
