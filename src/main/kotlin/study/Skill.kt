package study

data class Skill(val name: String, val type: SkillType) {

    enum class SkillType {
        SOFT,
        HARD
    }
}
