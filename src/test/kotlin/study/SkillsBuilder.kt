package study

class SkillsBuilder {
    val skillsList = mutableListOf<Skill>()

    fun soft(description: String) {
        skillsList.add(Skill(description, SkillType.SOFT.type))
    }

    fun hard(description: String) {
        skillsList.add(Skill(description, SkillType.HARD.type))
    }
}