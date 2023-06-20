package dsl

data class Skill(
    val name: String,
    val type: SkillType,
) {
    companion object {
        fun fromSoft(name: String): Skill {
            return Skill(name, SkillType.SOFT)
        }
        fun fromHard(name: String): Skill {
            return Skill(name, SkillType.HARD)
        }
    }
}

data class SkillBuilder(var skills: List<Skill> = emptyList()) {
    fun soft(value: String) {
        skills = skills.plus(Skill(value, SkillType.SOFT))
    }

    fun hard(value: String) {
        skills = skills.plus(Skill(value, SkillType.HARD))
    }
}

enum class SkillType {
    SOFT, HARD
}
