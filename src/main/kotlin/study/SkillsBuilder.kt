package study

class SkillsBuilder {
    private val skillsList = mutableListOf<Skill>()

    fun soft(value: String) {
        skillsList.add(Skill(SkillType.SOFT, value))
    }

    fun hard(value: String) {
        skillsList.add(Skill(SkillType.HARD, value))
    }

    fun build(): List<Skill> = skillsList
}
