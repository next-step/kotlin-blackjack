package dsl

data class Skills(
    private val _value: MutableList<Skill> = mutableListOf(),
) {
    val value: List<Skill>
        get() = _value

    fun soft(detail: String) {
        _value.add(Skill(SkillType.SOFT, detail))
    }

    fun hard(detail: String) {
        _value.add(Skill(SkillType.HARD, detail))
    }
}

data class Skill(
    val type: SkillType,
    val detail: String,
)

enum class SkillType {
    SOFT, HARD
}
