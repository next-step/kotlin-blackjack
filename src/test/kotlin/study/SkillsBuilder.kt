package study

class SkillsBuilder {
    private val list = mutableListOf<Skill>()
    fun soft(value: String) = list.add(Skill(value, SkillLevel.SOFT))
    fun hard(value: String) = list.add(Skill(value, SkillLevel.HARD))
    fun build(): List<Skill> = list
}
