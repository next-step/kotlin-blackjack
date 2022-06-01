package study.resume.skill

data class Skill(
    private val type: SkillType,
    private val desc: String
)
enum class SkillType {
    SOFT, HARD
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()
    fun soft(desc: String) {
        skills.add(Skill(SkillType.SOFT,desc))
    }

    fun hard(desc: String) {
        skills.add(Skill(SkillType.HARD,desc))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }

}

