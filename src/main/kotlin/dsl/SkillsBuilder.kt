package dsl

class SkillsBuilder {
    private val list = mutableListOf<Skill>()

    fun soft(skill: String) = list.add(Skill(Level.SOFT, skill))
    fun hard(skill: String) = list.add(Skill(Level.HARD, skill))
    fun build(): Skills = Skills(list)
}
