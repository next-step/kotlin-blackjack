data class Skill(val name: String, val type: SkillType)

enum class SkillType {
    Soft, Hard
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(name: String) {
        skills.add(Skill(name, SkillType.Soft))
    }

    fun hard(name: String) {
        skills.add(Skill(name, SkillType.Hard))
    }

    fun build(): List<Skill> = skills.toList()
}