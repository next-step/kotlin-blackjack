package study.builder

data class Skill(val name: String, val type: SkillType) {
    companion object {
        enum class SkillType {
            SOFT, HARD
        }
    }
}

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(skill: String) {
        skills.add(Skill(skill, Skill.Companion.SkillType.SOFT))
    }

    fun hard(skill: String) {
        skills.add(Skill(skill, Skill.Companion.SkillType.HARD))
    }

    fun build(): List<Skill> = skills
}
