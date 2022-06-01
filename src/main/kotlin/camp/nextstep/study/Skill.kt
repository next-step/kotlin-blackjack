package camp.nextstep.study

operator fun List<Skill>.plus(skill: Skill): List<Skill> {
    val result = this.toMutableList()
    result.add(skill)
    return result
}

fun List<Skill>.toSkills() = Skills(this)

data class Skill(val type: SkillType, val description: String) {
    enum class SkillType { SOFT, HARD }
}