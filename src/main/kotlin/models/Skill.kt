package models

data class Skill(
    val type: SkillType,
    val description: String
)

enum class SkillType {
    SOFT, HARD
}