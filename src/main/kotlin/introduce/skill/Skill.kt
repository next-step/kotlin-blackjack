package introduce.skill

import introduce.skill.SkillLevel.HARD
import introduce.skill.SkillLevel.SOFT

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
