package dsl

import dsl.SkillLevel.HARD
import dsl.SkillLevel.SOFT

data class Skill(
    val skillLevel: SkillLevel,
    val information: String,
) {
    companion object {
        fun softSkill(information: String) = Skill(SOFT, information)

        fun hardSkill(information: String) = Skill(HARD, information)
    }
}

enum class SkillLevel {
    SOFT,
    HARD,
}
