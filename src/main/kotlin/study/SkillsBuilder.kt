package study

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
        return Skills(soft.map { Skill.Soft(it) }.toList(), hard.map { Skill.Hard(it) }.toList())
    }
}
