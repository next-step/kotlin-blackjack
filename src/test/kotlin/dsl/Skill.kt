package dsl

data class SkillsBuilder(
    private var values: MutableList<Skill> = mutableListOf(),
) {
    fun soft(value: String) {
        values.add(Skill(value, SkillType.SOFT))
    }

    fun hard(value: String) {
        values.add(Skill(value, SkillType.HARD))
    }

    fun build(): Skills {
        return Skills(values)
    }
}

data class Skills(private val values: List<Skill> = emptyList()) : List<Skill> by values
data class Skill(val name: String, val type: SkillType)
enum class SkillType { SOFT, HARD }
