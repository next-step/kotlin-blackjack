package dsl

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(desc: String) {
        skills.add(Skill(desc, SkillType.Soft))
    }

    fun hard(desc: String) {
        skills.add(Skill(desc, SkillType.Hard))
    }

    fun build(): List<Skill> = skills.toList()
}

data class Skill(val desc: String, val type: SkillType)

enum class SkillType {
    Soft, Hard
}
